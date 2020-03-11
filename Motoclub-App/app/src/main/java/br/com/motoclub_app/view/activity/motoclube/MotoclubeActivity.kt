package br.com.motoclub_app.view.activity.motoclube

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.children
import br.com.motoclub_app.R
import br.com.motoclub_app.app.utils.DateUtils
import br.com.motoclub_app.app.utils.ImageUtils
import br.com.motoclub_app.model.Motoclube
import br.com.motoclub_app.repository.user.UserCacheRepository
import br.com.motoclub_app.view.activity.BaseActivity
import br.com.motoclub_app.view.activity.motoclube.contract.MotoclubeActivityView
import br.com.motoclub_app.view.fragment.BottomNavigationFragment
import com.afollestad.vvalidator.form
import com.afollestad.vvalidator.form.Form
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.activity_motoclube.*

class MotoclubeActivity : BaseActivity<MotoclubeActivityPresenterImpl>(), MotoclubeActivityView {

    private lateinit var myForm: Form

    var motoclube: Motoclube = Motoclube()

    private var isEdit = false
    private var isReadOnly = false
    private var imagePath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motoclube)

        intent.extras?.apply {
            if (this.containsKey("readOnly")) {
                isReadOnly = this.getBoolean("readOnly")
            }
        }

        title = "Cadastro de Motoclube"

        setSupportActionBar(motoclube_toolbar)

        initForm()
        initData()
        initFormValidation()
        initListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.motoclube_menu, menu)

        if (UserCacheRepository.currentUser!!.motoclubeRef == null) {
            menu?.findItem(R.id.motoclube_menu_sair)?.isVisible = false
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            // R.id.motoclube_menu_integrantes ->
            R.id.motoclube_menu_sair -> presenter.quit()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initForm() {

        if (isReadOnly) {

            activity_motoclube_add_photo_card.visibility = View.GONE
            activity_motoclube_btn_salvar.visibility = View.GONE

            activity_motoclube_form.children.forEach {

                if (it !is ImageView && it !is Button)
                    it.isEnabled = false
            }

            if (UserCacheRepository.currentUser!!.motoclubeRef == null) {
                activity_motoclube_btn_solicitar_entrada.visibility = View.VISIBLE
            }
        }

        if (!isEdit) {

            // Novo cadastro não há integrantes, portanto quem cria será o presidente até editar e adicionar mais integrantes
            if (activity_motoclube_presidente.text.toString().isEmpty()) {
                val nomePresidente =
                    UserCacheRepository.currentUser!!.apelido ?: UserCacheRepository.currentUser!!.nome
                activity_motoclube_presidente.setText(nomePresidente)
            }
            activity_motoclube_presidente.isEnabled = false
        }
    }

    private fun initData() {
        intent.extras?.apply {

            Log.i(TAG, "Have extras")
            if (this.containsKey("id")) {

                isEdit = true

                Log.i(TAG, "Have an ID Extra")
                val mcId = getString("id")

                Log.i(TAG, "Loading user from repository")
                presenter.loadById(mcId!!)
            }
        }
    }

    private fun initFormValidation() {
        myForm = form {
            inputLayout(R.id.input_layout_motoclube_name) {
                isNotEmpty().description(getString(R.string.campo_obrigatorio))
            }

            inputLayout(R.id.input_layout_motoclube_fundacao) {

                conditional({ activity_motoclube_fundacao.text.toString().isNotBlank() }) {
                    matches("^\\d{2}/\\d{2}/\\d{4}$").description(getString(R.string.invalid_date))
                }
            }
        }
    }

    private fun initListeners() {

        activity_motoclube_img.setOnClickListener {

            val image =
                if (imagePath != null) {
                    imagePath.toString()
                } else {
                    motoclube.imageId
                }

            image?.let { ImageUtils.openImageViewer(this, it) }
        }

        if (!isReadOnly) {
            activity_motoclube_add_photo_card.setOnClickListener {

                val bottomNav = BottomNavigationFragment()

                bottomNav.show(supportFragmentManager, bottomNav.tag)

                bottomNav.onImageSelectedListener {

                    updateImage(it)
                    imagePath = it
                    bottomNav.dismiss()
                }
            }
        }

        activity_motoclube_btn_solicitar_entrada.setOnClickListener {
            presenter.requestEntrance(motoclube.id!!)
        }

        MaskedTextChangedListener.installOn(activity_motoclube_fundacao, "[00]/[00]/[0000]")
        activity_motoclube_btn_salvar.setOnClickListener { save() }
    }

    private fun save() {
        Log.i(TAG, "Validating form")
        val result = myForm.validate()

        if (result.success()) {
            Log.i(TAG, "Saving the motoclubeRef")
            motoclube.apply {
                nome = activity_motoclube_name.text.toString()
                presidenteRef = presenter.getUserReference(UserCacheRepository.currentUser!!.id!!)
                presidenteNome = UserCacheRepository.currentUser!!.apelido ?: UserCacheRepository.currentUser!!.nome

                if (activity_motoclube_fundacao.text.toString().isNotBlank()) {
                    dataFundacao =
                        DateUtils.stringToTimestamp(activity_motoclube_fundacao.text.toString())
                }

                imagePath?.apply {
                    imageId = this.toString()
                }
            }
            presenter.save(motoclube)
        } else {

            Log.i(TAG, result.errors().toString())
        }
    }

    private fun updateImage(uri: Uri?) {
        ImageUtils.loadImage(this, uri.toString(), activity_motoclube_img)
    }

    override fun onSave() {
        finish()
    }

    override fun onLoadMotoclube(mc: Motoclube?) {

        mc?.let {
            motoclube = it
        }

        activity_motoclube_name.setText(motoclube.nome)

        val presidenteNome =motoclube.presidenteNome?: ""

        activity_motoclube_presidente.setText(presidenteNome)

        motoclube.dataFundacao?.let {
            activity_motoclube_fundacao.setText(DateUtils.timestampToString(it))
        }

        motoclube.imageId?.let {
            updateImage(Uri.parse(it))
        }

        title = "MC ${motoclube.nome}"
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    companion object {
        val TAG = MotoclubeActivity::class.java.simpleName
    }
}

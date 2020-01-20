package br.com.motoclub_app.view.activity.motoclube

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import br.com.motoclub_app.R
import br.com.motoclub_app.app.utils.DateUtils
import br.com.motoclub_app.app.utils.ImageUtils
import br.com.motoclub_app.model.Motoclube
import br.com.motoclub_app.repository.UserRepository
import br.com.motoclub_app.view.activity.BaseActivity
import br.com.motoclub_app.view.activity.motoclube.contract.MotoclubeActivityView
import br.com.motoclub_app.view.fragment.BottomNavigationFragment
import com.afollestad.vvalidator.form
import com.afollestad.vvalidator.form.Form
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.activity_motoclube.*

class MotoclubeActivity : BaseActivity<MotoclubeActivityPresenterImpl>(), MotoclubeActivityView {

    private lateinit var myForm: Form
    lateinit var motoclube: Motoclube

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

        if (UserRepository.loggedUser!!.motoclubeId == null) {
            menu?.findItem(R.id.motoclube_menu_sair)?.isVisible = false
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            // R.id.motoclube_menu_integrantes ->
            R.id.motoclube_menu_sair -> presenter.sair()
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

            if (UserRepository.loggedUser!!.motoclubeId == null) {
                activity_motoclube_btn_solicitar_entrada.visibility = View.VISIBLE
            }
        }

        if (!isEdit) {

            // Novo cadastro não há integrantes, portanto quem cria será o presidente até editar e adicionar mais integrantes
            if (activity_motoclube_presidente.text.toString().isEmpty()) {
                val nomePresidente =
                    UserRepository.loggedUser!!.apelido ?: UserRepository.loggedUser!!.nome
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
                val mcId = getLong("id")

                Log.i(TAG, "Loading user from repository")
                motoclube = presenter.loadById(mcId)

                activity_motoclube_name.setText(motoclube.nome)

                val presidenteName =
                    motoclube.presidente?.apelido ?: motoclube.presidente?.nome ?: ""
                activity_motoclube_presidente.setText(presidenteName)

                motoclube.dataFundacao?.let {
                    activity_motoclube_fundacao.setText(
                        DateUtils.calendarToString(
                            it
                        )
                    )
                }

                motoclube.imageId?.let {
                    updateImage(Uri.parse(it))
                }

                title = "MC ${motoclube.nome}"
            }
        }

        if (!::motoclube.isInitialized)
            motoclube = Motoclube()
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
            presenter.solicitarEntrada(motoclube.id!!)
        }

        MaskedTextChangedListener.installOn(activity_motoclube_fundacao, "[00]/[00]/[0000]")
        activity_motoclube_btn_salvar.setOnClickListener { salvar() }
    }

    private fun salvar() {
        Log.i(TAG, "Validating form")
        val result = myForm.validate()

        if (result.success()) {
            Log.i(TAG, "Saving the motoclubeId")
            motoclube.apply {
                nome = activity_motoclube_name.text.toString()
                presidente = UserRepository.loggedUser!!

                if (activity_motoclube_fundacao.text.toString().isNotBlank()) {
                    dataFundacao =
                        DateUtils.stringToCalendar(activity_motoclube_fundacao.text.toString())
                }

                imagePath?.apply {
                    imageId = this.toString()
                }
            }
            presenter.salvar(motoclube)
        } else {

            Log.i(TAG, result.errors().toString())
        }
    }

    private fun updateImage(uri: Uri?) {
        ImageUtils.loadImage(this, uri.toString(), activity_motoclube_img)
    }

    override fun onSalvar() {
        finish()
    }

    override fun showError(msg: String?) {
        Toast.makeText(this, msg ?: "Houve um erro ao salvar", Toast.LENGTH_LONG).show()
    }

    companion object {
        val TAG = MotoclubeActivity::class.java.simpleName
    }
}

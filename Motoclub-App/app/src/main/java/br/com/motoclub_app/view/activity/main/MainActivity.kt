package br.com.motoclub_app.view.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import br.com.motoclub_app.R
import br.com.motoclub_app.app.Permissions
import br.com.motoclub_app.app.utils.ImageUtils
import br.com.motoclub_app.repository.UserRepository
import br.com.motoclub_app.type.EventType
import br.com.motoclub_app.view.activity.BaseActivity
import br.com.motoclub_app.view.activity.login.LoginActivity
import br.com.motoclub_app.view.activity.main.contract.MainPresenter
import br.com.motoclub_app.view.activity.main.contract.MainView
import br.com.motoclub_app.view.activity.motoclube.MotoclubeActivity
import br.com.motoclub_app.view.activity.user.UserActivity
import br.com.motoclub_app.view.fragment.evento.lista.EventosFragment
import br.com.motoclub_app.view.fragment.integrante.lista.IntegrantesFragment
import br.com.motoclub_app.view.fragment.motoclube.lista.MotoclubesFragment
import com.google.android.material.navigation.NavigationView
import javax.inject.Inject

class MainActivity : BaseActivity<MainPresenter>(), MainView, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var permissions: Permissions

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        requestPermissions()

        val fragment: Fragment =
            if (UserRepository.loggedUser?.motoclubeId == null) MotoclubesFragment() else IntegrantesFragment()
        setFragment(fragment)
    }

    override fun onResume() {
        super.onResume()

        val navView: NavigationView = findViewById(R.id.nav_view)
        configureUserHeader(navView)
        configureMenu(navView)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_integrantes -> setFragment(IntegrantesFragment())
            R.id.nav_meu_motoclube -> openMyMotoclube()
            R.id.nav_motoclubes -> setFragment(MotoclubesFragment())
            R.id.nav_evento_privado -> setFragment(EventosFragment.newInstance(EventType.PRIVATE))
            R.id.nav_evento_publico -> setFragment(EventosFragment.newInstance(EventType.PUBLIC))
            R.id.nav_sair -> presenter.logout()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onLogout() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    private fun requestPermissions() {
        permissions.addCameraPermission()
        permissions.addStoragePermission()
        permissions.validateAndRequestPermission(this)
    }

    private fun configureUserHeader(navView: NavigationView) {

        UserRepository.loggedUser!!.let { user ->

            val headerView = navView.getHeaderView(0)
            val perfilName =
                if (user.apelido != null && user.apelido.toString().isNotEmpty()) user.apelido else user.nome
            headerView.findViewById<TextView>(R.id.nav_perfil_name).text = perfilName

            presenter.getMotoclube()?.let { mc ->
                headerView.findViewById<TextView>(R.id.nav_perfil_motoclube_name).text = mc.nome
            }

            user.imageId?.let { image ->
                ImageUtils.loadImage(this, image, headerView.findViewById(R.id.nav_perfil_image))
            }

            headerView.findViewById<ConstraintLayout>(R.id.nav_perfil).setOnClickListener {

                UserRepository.loggedUser!!.imageId?.let {
                    ImageUtils.openImageViewer(this, it)
                }
            }

            headerView.findViewById<ImageView>(R.id.nav_perfil_edit).setOnClickListener {
                val userIntent = Intent(this, UserActivity::class.java)
                userIntent.putExtra("id", user.id)
                startActivity(userIntent)
            }
        }
    }

    private fun configureMenu(navView: NavigationView) {
        UserRepository.loggedUser!!.let { user ->
            navView.menu.findItem(R.id.nav_meu_motoclube).isVisible = (user.imageId != null)
        }
    }

    private fun openMyMotoclube() {
        val intent = Intent(this, MotoclubeActivity::class.java)
        intent.putExtra("id", UserRepository.loggedUser!!.motoclubeId)
        intent.putExtra("readOnly", false)
        startActivity(intent)
    }
}

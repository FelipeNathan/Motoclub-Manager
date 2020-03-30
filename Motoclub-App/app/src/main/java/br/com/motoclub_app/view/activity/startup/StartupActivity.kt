package br.com.motoclub_app.view.activity.startup

import android.os.Bundle
import br.com.motoclub_app.R
import br.com.motoclub_app.view.activity.BaseActivity
import br.com.motoclub_app.view.activity.startup.contract.StartupPresenter
import br.com.motoclub_app.view.activity.startup.contract.StartupView

class StartupActivity : BaseActivity<StartupPresenter>(), StartupView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_startup)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter.onCreate()
    }
}

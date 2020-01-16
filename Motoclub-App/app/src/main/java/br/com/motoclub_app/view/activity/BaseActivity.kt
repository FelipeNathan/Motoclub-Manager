package br.com.motoclub_app.view.activity

import br.com.motoclub_app.core.contract.Presenter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<T : Presenter> : DaggerAppCompatActivity() {

    @Inject
    lateinit var presenter: T
}
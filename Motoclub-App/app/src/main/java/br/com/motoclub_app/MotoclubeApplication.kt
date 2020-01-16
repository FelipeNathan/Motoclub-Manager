package br.com.motoclub_app

import br.com.motoclub_app.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MotoclubeApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out MotoclubeApplication> {

        val app = DaggerAppComponent.builder().application(this).build()
        app.inject(this)

        return app
    }
}
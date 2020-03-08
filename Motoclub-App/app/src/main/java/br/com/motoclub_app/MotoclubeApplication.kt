package br.com.motoclub_app

import android.content.Context
import androidx.multidex.MultiDex
import br.com.motoclub_app.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MotoclubeApplication : DaggerApplication() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun applicationInjector(): AndroidInjector<out MotoclubeApplication> {
        appComponent.inject(this)
        return appComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
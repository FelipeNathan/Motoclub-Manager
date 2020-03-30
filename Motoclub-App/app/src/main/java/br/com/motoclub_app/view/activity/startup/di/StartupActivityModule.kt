package br.com.motoclub_app.view.activity.startup.di

import br.com.motoclub_app.app.scope.PerActivity
import br.com.motoclub_app.view.activity.startup.StartupActivity
import br.com.motoclub_app.view.activity.startup.contract.StartupPresenter
import br.com.motoclub_app.view.activity.startup.contract.StartupView
import dagger.Binds
import dagger.Module

@Module
abstract class StartupActivityModule {

    @Binds
    @PerActivity
    abstract fun provideView(view: StartupActivity): StartupView

    @Binds
    @PerActivity
    abstract fun providePresenter(presenter: StartupPresenterImpl): StartupPresenter
}
package br.com.motoclub_app.view.activity.main.di

import br.com.motoclub_app.app.scope.PerActivity
import br.com.motoclub_app.view.activity.main.MainActivity
import br.com.motoclub_app.view.activity.main.MainPresenterImpl
import br.com.motoclub_app.view.activity.main.contract.MainPresenter
import br.com.motoclub_app.view.activity.main.contract.MainView
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    @PerActivity
    abstract fun provideView(view: MainActivity): MainView

    @Binds
    @PerActivity
    abstract fun providePresenter(presenter: MainPresenterImpl): MainPresenter
}
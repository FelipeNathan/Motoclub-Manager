package br.com.motoclub_app.view.activity.login.di

import br.com.motoclub_app.view.activity.login.LoginActivity
import br.com.motoclub_app.view.activity.login.LoginPresenterImpl
import br.com.motoclub_app.view.activity.login.contract.LoginPresenter
import br.com.motoclub_app.view.activity.login.contract.LoginView
import br.com.motoclub_app.app.scope.PerActivity
import dagger.Binds
import dagger.Module

@Module
abstract class LoginActivityModule {

    @Binds
    @PerActivity
    abstract fun provideView(view: LoginActivity): LoginView

    @Binds
    @PerActivity
    abstract fun providePresenter(presenter: LoginPresenterImpl): LoginPresenter
}
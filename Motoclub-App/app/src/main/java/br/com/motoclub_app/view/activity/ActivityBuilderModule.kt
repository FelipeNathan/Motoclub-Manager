package br.com.motoclub_app.view.activity

import br.com.motoclub_app.view.activity.login.LoginActivity
import br.com.motoclub_app.view.activity.login.di.LoginActivityModule
import br.com.motoclub_app.view.activity.main.MainActivity
import br.com.motoclub_app.view.activity.main.di.MainActivityModule
import br.com.motoclub_app.view.activity.user.UserActivity
import br.com.motoclub_app.view.activity.user.di.UserActivityModule
import br.com.motoclub_app.app.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [UserActivityModule::class])
    abstract fun contributeUserActivity(): UserActivity
}
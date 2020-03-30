package br.com.motoclub_app.view.activity

import br.com.motoclub_app.view.activity.login.LoginActivity
import br.com.motoclub_app.view.activity.login.di.LoginActivityModule
import br.com.motoclub_app.view.activity.main.MainActivity
import br.com.motoclub_app.view.activity.main.di.MainActivityModule
import br.com.motoclub_app.view.activity.user.UserActivity
import br.com.motoclub_app.view.activity.user.di.UserActivityModule
import br.com.motoclub_app.app.scope.PerActivity
import br.com.motoclub_app.view.activity.motoclube.MotoclubeActivity
import br.com.motoclub_app.view.activity.motoclube.di.MotoclubeActivityModule
import br.com.motoclub_app.view.activity.startup.StartupActivity
import br.com.motoclub_app.view.activity.startup.di.StartupActivityModule
import br.com.motoclub_app.view.fragment.MainFragmentBuilderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [StartupActivityModule::class])
    abstract fun contributeStartupActivity(): StartupActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainFragmentBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [UserActivityModule::class])
    abstract fun contributeUserActivity(): UserActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [MotoclubeActivityModule::class])
    abstract fun contributeMotoclubeActivity(): MotoclubeActivity
}
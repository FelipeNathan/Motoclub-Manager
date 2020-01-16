package br.com.motoclub_app.view.activity.user.di

import br.com.motoclub_app.view.activity.user.UserActivity
import br.com.motoclub_app.view.activity.user.UserPresenterImpl
import br.com.motoclub_app.view.activity.user.contract.UserPresenter
import br.com.motoclub_app.view.activity.user.contract.UserView
import br.com.motoclub_app.app.scope.PerActivity
import dagger.Binds
import dagger.Module

@Module
abstract class UserActivityModule {

    @Binds
    @PerActivity
    abstract fun providePresenter(presenter: UserPresenterImpl) : UserPresenter

    @Binds
    @PerActivity
    abstract fun provideView(activity: UserActivity): UserView
}
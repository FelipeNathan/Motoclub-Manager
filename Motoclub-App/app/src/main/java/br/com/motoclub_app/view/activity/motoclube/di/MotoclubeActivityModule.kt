package br.com.motoclub_app.view.activity.motoclube.di

import br.com.motoclub_app.app.scope.PerActivity
import br.com.motoclub_app.view.activity.motoclube.MotoclubeActivity
import br.com.motoclub_app.view.activity.motoclube.MotoclubeActivityPresenterImpl
import br.com.motoclub_app.view.activity.motoclube.contract.MotoclubeActivityPresenter
import br.com.motoclub_app.view.activity.motoclube.contract.MotoclubeActivityView
import dagger.Binds
import dagger.Module

@Module
abstract class MotoclubeActivityModule {

    @Binds
    @PerActivity
    abstract fun providePresenter(presenter: MotoclubeActivityPresenterImpl): MotoclubeActivityPresenter

    @Binds
    @PerActivity
    abstract fun provideView(view: MotoclubeActivity): MotoclubeActivityView
}
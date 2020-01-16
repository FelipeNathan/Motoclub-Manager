package br.com.motoclub_app.view.fragment.motoclube.lista.di

import br.com.motoclub_app.app.scope.PerFragment
import br.com.motoclub_app.view.fragment.motoclube.lista.MotoclubesPresenterImpl
import br.com.motoclub_app.view.fragment.motoclube.lista.MotoclubesFragment
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesPresenter
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesView
import dagger.Binds
import dagger.Module

@Module
abstract class MotoclubeFragmentModule {

    @Binds
    @PerFragment
    abstract fun providePresenter(presenter: MotoclubesPresenterImpl): MotoclubesPresenter

    @Binds
    @PerFragment
    abstract fun provideView(view: MotoclubesFragment): MotoclubesView
}
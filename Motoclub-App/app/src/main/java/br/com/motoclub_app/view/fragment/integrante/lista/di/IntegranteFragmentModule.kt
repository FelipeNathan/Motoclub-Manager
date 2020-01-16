package br.com.motoclub_app.view.fragment.integrante.lista.di

import br.com.motoclub_app.app.scope.PerFragment
import br.com.motoclub_app.view.fragment.integrante.lista.IntegrantesFragment
import br.com.motoclub_app.view.fragment.integrante.lista.IntegrantesPresenterImpl
import br.com.motoclub_app.view.fragment.integrante.lista.contract.IntegrantesPresenter
import br.com.motoclub_app.view.fragment.integrante.lista.contract.IntegrantesView
import dagger.Binds
import dagger.Module

@Module
abstract class IntegranteFragmentModule {

    @Binds
    @PerFragment
    abstract fun providePresenter(presenter: IntegrantesPresenterImpl): IntegrantesPresenter

    @Binds
    @PerFragment
    abstract fun provideView(view: IntegrantesFragment): IntegrantesView
}
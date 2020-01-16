package br.com.motoclub_app.view.fragment.evento.lista.di

import br.com.motoclub_app.app.scope.PerFragment
import br.com.motoclub_app.view.fragment.evento.lista.EventosFragment
import br.com.motoclub_app.view.fragment.evento.lista.EventosPresenterImpl
import br.com.motoclub_app.view.fragment.evento.lista.contract.EventosPresenter
import br.com.motoclub_app.view.fragment.evento.lista.contract.EventosView
import dagger.Binds
import dagger.Module

@Module
abstract class EventosFragmentModule {

    @Binds
    @PerFragment
    abstract fun providePresenter(presenter: EventosPresenterImpl): EventosPresenter

    @Binds
    @PerFragment
    abstract fun provideView(view: EventosFragment): EventosView
}
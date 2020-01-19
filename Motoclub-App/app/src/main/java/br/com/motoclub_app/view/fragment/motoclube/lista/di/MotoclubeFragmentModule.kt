package br.com.motoclub_app.view.fragment.motoclube.lista.di

import br.com.motoclub_app.app.scope.PerFragment
import br.com.motoclub_app.view.fragment.motoclube.lista.MotoclubesFragmentPresenterImpl
import br.com.motoclub_app.view.fragment.motoclube.lista.MotoclubesFragment
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesFragmentPresenter
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesFragmentView
import dagger.Binds
import dagger.Module

@Module
abstract class MotoclubeFragmentModule {

    @Binds
    @PerFragment
    abstract fun providePresenter(presenter: MotoclubesFragmentPresenterImpl): MotoclubesFragmentPresenter

    @Binds
    @PerFragment
    abstract fun provideView(view: MotoclubesFragment): MotoclubesFragmentView
}
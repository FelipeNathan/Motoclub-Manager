package br.com.motoclub_app.view.fragment

import br.com.motoclub_app.app.scope.PerFragment
import br.com.motoclub_app.view.fragment.evento.lista.EventosFragment
import br.com.motoclub_app.view.fragment.evento.lista.di.EventosFragmentModule
import br.com.motoclub_app.view.fragment.integrante.lista.IntegrantesFragment
import br.com.motoclub_app.view.fragment.integrante.lista.di.IntegranteFragmentModule
import br.com.motoclub_app.view.fragment.motoclube.lista.MotoclubesFragment
import br.com.motoclub_app.view.fragment.motoclube.lista.di.MotoclubeFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [IntegranteFragmentModule::class])
    abstract fun contributeIntegranteFragment(): IntegrantesFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [MotoclubeFragmentModule::class])
    abstract fun contributeMotoclubeFragment(): MotoclubesFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [EventosFragmentModule::class])
    abstract fun contributeEventosFragment(): EventosFragment
}
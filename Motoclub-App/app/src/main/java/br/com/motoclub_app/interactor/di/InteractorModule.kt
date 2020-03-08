package br.com.motoclub_app.interactor.di

import br.com.motoclub_app.interactor.firebase.FirebaseInteractor
import br.com.motoclub_app.interactor.firebase.FirebaseInteractorImpl
import br.com.motoclub_app.interactor.motoclube.MotoclubeInteractor
import br.com.motoclub_app.interactor.motoclube.MotoclubeInteractorImpl
import br.com.motoclub_app.interactor.user.UserInteractor
import br.com.motoclub_app.interactor.user.UserInteractorImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class InteractorModule {

    @Binds
    @Singleton
    abstract fun provideMotoclubeInteractor(view: MotoclubeInteractorImpl): MotoclubeInteractor

    @Binds
    @Singleton
    abstract fun provideUserInteractor(view: UserInteractorImpl): UserInteractor

    @Binds
    @Singleton
    abstract fun provideFirebaseInteractor(view: FirebaseInteractorImpl): FirebaseInteractor
}
package br.com.motoclub_app.interactor.di

import br.com.motoclub_app.interactor.firebase.FirebaseInteractor
import br.com.motoclub_app.interactor.firebase.FirebaseInteractorImpl
import br.com.motoclub_app.interactor.motoclube.MotoclubeInteractor
import br.com.motoclub_app.interactor.motoclube.MotoclubeInteractorImpl
import br.com.motoclub_app.interactor.request.RequestInteractor
import br.com.motoclub_app.interactor.request.RequestInteractorImpl
import br.com.motoclub_app.interactor.user.UserInteractor
import br.com.motoclub_app.interactor.user.UserInteractorImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class InteractorModule {

    @Binds
    @Singleton
    abstract fun provideMotoclubeInteractor(interactor: MotoclubeInteractorImpl): MotoclubeInteractor

    @Binds
    @Singleton
    abstract fun provideUserInteractor(interactor: UserInteractorImpl): UserInteractor

    @Binds
    @Singleton
    abstract fun provideFirebaseInteractor(interactor: FirebaseInteractorImpl): FirebaseInteractor

    @Binds
    @Singleton
    abstract fun provideRequestInteractor(interactor: RequestInteractorImpl): RequestInteractor
}
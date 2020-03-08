package br.com.motoclub_app.app.di

import android.app.Application
import br.com.motoclub_app.MotoclubeApplication
import br.com.motoclub_app.interactor.di.InteractorModule
import br.com.motoclub_app.view.activity.ActivityBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        AppModule::class,
        InteractorModule::class
    ]
)
interface AppComponent : AndroidInjector<MotoclubeApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance motoclubeApplication: Application): AppComponent
    }
}
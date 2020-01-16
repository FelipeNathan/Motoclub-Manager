package br.com.motoclub_app.app.di

import android.app.Application
import br.com.motoclub_app.MotoclubeApplication
import br.com.motoclub_app.model.Motoclube
import br.com.motoclub_app.view.activity.ActivityBuilderModule
import br.com.motoclub_app.view.fragment.FragmentBuilderModule
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
        FragmentBuilderModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<MotoclubeApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(motoclubeApplication: Application): Builder

        fun build(): AppComponent
    }
}
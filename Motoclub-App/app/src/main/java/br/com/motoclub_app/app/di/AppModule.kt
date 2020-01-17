package br.com.motoclub_app.app.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import br.com.motoclub_app.app.Permissions
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideContext(application: Application): Context

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provideSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences("motoclube_app", Context.MODE_PRIVATE)

        @JvmStatic
        @Provides
        @Singleton
        fun providePermissions(context: Context) = Permissions(context)

        @JvmStatic
        @Provides
        @Singleton
        fun provideGson() = Gson()
    }
}
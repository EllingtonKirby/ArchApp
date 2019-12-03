package com.ellington.dagger.modules

import android.app.Application
import com.google.gson.Gson
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Component(modules = [CoreModule::class])
@Singleton
interface CoreComponent {

    @Component.Builder
    interface Builder {
        fun build(): CoreComponent
        @BindsInstance
        fun application(application: Application): Builder
    }

    fun providesGson(): Gson
    fun providesOkHttpClient(): OkHttpClient
    fun providesApplication(): Application
}
package com.ellington.dagger.modules

import com.ellington.dagger.utils.PerApplication
import com.google.gson.Gson
import dagger.Component
import okhttp3.OkHttpClient

@Component(modules = [NetworkingModule::class])
@PerApplication
interface CoreComponent {

    @Component.Builder
    interface Builder {
        fun build(): CoreComponent
    }

    fun providesGson(): Gson
    fun providesOkHttpClient(): OkHttpClient
}
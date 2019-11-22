package com.ellington.dagger.components

import android.app.Application
import com.ellington.architecture.ArchApplication
import com.ellington.architecture.dagger.ActivityBuilder
import com.ellington.architecture.dagger.ViewModelBuilder
import com.ellington.dagger.utils.PerApplication
import com.ellington.network.NetworkingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelBuilder::class,
        ActivityBuilder::class,
        NetworkingModule::class
    ]
)

interface AppComponent : AndroidInjector<ArchApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: Application)
}
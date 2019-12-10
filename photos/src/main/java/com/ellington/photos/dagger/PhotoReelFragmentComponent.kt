package com.ellington.photos.dagger

import com.ellington.dagger.modules.BaseFragmentComponent
import com.ellington.dagger.modules.CoreComponent
import com.ellington.dagger.utils.PerApplication
import com.ellington.photos.view.PhotoReelFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [PhotoReelFragmentModule::class], dependencies = [CoreComponent::class])
@PerApplication
interface PhotoReelFragmentComponent : BaseFragmentComponent<PhotoReelFragment> {

    @Component.Builder
    interface Builder {
        fun build(): PhotoReelFragmentComponent
        @BindsInstance
        fun photoReelFragment(fragment: PhotoReelFragment): Builder

        fun coreComponent(module: CoreComponent): Builder
    }
}
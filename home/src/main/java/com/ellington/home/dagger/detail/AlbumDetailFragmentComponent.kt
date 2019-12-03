package com.ellington.home.dagger.detail

import com.ellington.dagger.modules.BaseFragmentComponent
import com.ellington.dagger.modules.CoreComponent
import com.ellington.dagger.utils.PerApplication
import com.ellington.home.dagger.CommonAlbumsModule
import com.ellington.home.view.details.AlbumDetailFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AlbumDetailFragmentModule::class, CommonAlbumsModule::class], dependencies = [CoreComponent::class])
@PerApplication
interface AlbumDetailFragmentComponent : BaseFragmentComponent<AlbumDetailFragment> {

    @Component.Builder
    interface Builder {
        fun build(): AlbumDetailFragmentComponent
        @BindsInstance
        fun albumViewingFragment(fragment: AlbumDetailFragment): Builder

        fun coreComponent(module: CoreComponent): Builder
    }
}
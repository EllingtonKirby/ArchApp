package com.ellington.home.dagger.list

import com.ellington.dagger.modules.BaseFragmentComponent
import com.ellington.dagger.modules.CoreComponent
import com.ellington.dagger.utils.PerApplication
import com.ellington.home.view.AlbumViewingFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AlbumListFragmentModule::class], dependencies = [CoreComponent::class])
@PerApplication
interface AlbumListFragmentComponent : BaseFragmentComponent<AlbumViewingFragment> {

    @Component.Builder
    interface Builder {
        fun build(): AlbumListFragmentComponent
        @BindsInstance
        fun albumViewingFragment(fragment: AlbumViewingFragment): Builder

        fun coreComponent(module: CoreComponent): Builder
    }
}
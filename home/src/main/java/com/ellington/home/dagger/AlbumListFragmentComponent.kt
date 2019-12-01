package com.ellington.home.dagger

import com.ellington.dagger.modules.BaseFragmentComponent
import com.ellington.dagger.modules.CoreComponent
import com.ellington.dagger.utils.PerActivity
import com.ellington.home.view.AlbumListFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AlbumListFragmentModule::class], dependencies = [CoreComponent::class])
@PerActivity
interface AlbumListFragmentComponent : BaseFragmentComponent<AlbumListFragment> {

    @Component.Builder
    interface Builder {
        fun build(): AlbumListFragmentComponent
        @BindsInstance
        fun albumListFragment(fragment: AlbumListFragment): Builder

        fun coreComponent(module: CoreComponent): Builder
    }
}
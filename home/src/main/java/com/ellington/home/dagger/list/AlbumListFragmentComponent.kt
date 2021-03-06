package com.ellington.home.dagger.list

import com.ellington.dagger.modules.BaseFragmentComponent
import com.ellington.dagger.modules.CoreComponent
import com.ellington.dagger.utils.PerApplication
import com.ellington.home.dagger.CommonAlbumsModule
import com.ellington.home.view.details.AlbumDetailFragment
import com.ellington.home.view.list.AlbumListFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AlbumListFragmentModule::class, CommonAlbumsModule::class], dependencies = [CoreComponent::class])
@PerApplication
interface AlbumListFragmentComponent : BaseFragmentComponent<AlbumListFragment> {

    @Component.Builder
    interface Builder {
        fun build(): AlbumListFragmentComponent
        @BindsInstance
        fun albumViewingFragment(fragment: AlbumListFragment): Builder
        fun coreComponent(module: CoreComponent): Builder
    }
}
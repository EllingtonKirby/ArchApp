@file:JvmName("Injector")

package com.ellington.home.dagger

import com.ellington.architecture.ArchApplication
import com.ellington.home.dagger.detail.DaggerAlbumDetailFragmentComponent
import com.ellington.home.dagger.list.DaggerAlbumListFragmentComponent
import com.ellington.home.view.details.AlbumDetailFragment
import com.ellington.home.view.list.AlbumListFragment

fun inject(fragment: AlbumListFragment) {
    DaggerAlbumListFragmentComponent.builder().albumViewingFragment(fragment)
        .coreComponent(ArchApplication.coreComponent(fragment.context))
        .build()
        .inject(fragment)
}

fun inject(fragment: AlbumDetailFragment) {
    DaggerAlbumDetailFragmentComponent.builder().albumViewingFragment(fragment)
        .coreComponent(ArchApplication.coreComponent(fragment.context))
        .build()
        .inject(fragment)
}
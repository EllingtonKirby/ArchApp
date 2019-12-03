@file:JvmName("Injector")

package com.ellington.home.dagger

import com.ellington.architecture.ArchApplication
import com.ellington.home.dagger.list.DaggerAlbumListFragmentComponent
import com.ellington.home.view.AlbumViewingFragment

fun inject(fragment: AlbumViewingFragment) {
    DaggerAlbumListFragmentComponent.builder().albumViewingFragment(fragment)
        .coreComponent(ArchApplication.coreComponent(fragment.context))
        .build()
        .inject(fragment)
}
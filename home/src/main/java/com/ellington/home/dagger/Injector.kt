@file:JvmName("Injector")

package com.ellington.home.dagger

import com.ellington.architecture.ArchApplication
import com.ellington.home.view.AlbumListFragment

fun inject(fragment: AlbumListFragment) {
    DaggerAlbumListFragmentComponent.builder().albumListFragment(fragment)
        .coreComponent(ArchApplication.coreComponent(fragment.context))
        .build()
        .inject(fragment)
}
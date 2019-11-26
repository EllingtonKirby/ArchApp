@file:JvmName("Injector")

package com.ellington.home.dagger

import com.ellington.architecture.ArchApplication
import com.ellington.home.view.PictureListFragment

fun inject(fragment: PictureListFragment) {
    DaggerPictureListFragmentComponent.builder().pictureListFragment(fragment)
        .coreComponent(ArchApplication.coreComponent(fragment.context))
        .build()
        .inject(fragment)
}
package com.ellington.photos.dagger

import com.ellington.architecture.ArchApplication
import com.ellington.photos.view.PhotoReelFragment

fun inject(fragment: PhotoReelFragment) {
    DaggerPhotoReelFragmentComponent.builder().photoReelFragment(fragment)
        .coreComponent(ArchApplication.coreComponent(fragment.context))
        .build()
        .inject(fragment)
}

package com.ellington.photos.view

import android.os.Bundle
import com.ellington.mvvm.base.BaseActivity
import com.ellington.photos.R

class PhotoReelActivity(override val layoutResourceId: Int = R.layout.activity_photo_reel) :
    BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .replace(R.id.photo_reel_container, PhotoReelFragment.newInstance())
            .commit()
    }
}
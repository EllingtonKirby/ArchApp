package com.ellington.home.view

import android.os.Bundle
import com.ellington.home.R
import com.ellington.mvvm.base.BaseActivity
import com.ellington.mvvm.utils.Event

class HomeActivity(override val layoutResourceId: Int = R.layout.activity_home) : BaseActivity(),
    HomeNavigationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_frame_layout, AlbumListFragment.newInstance())
                .commit()
        }
    }

    override fun onAlbumSelected(event: Event<String>) {
        supportFragmentManager.beginTransaction()
            .add(R.id.home_frame_layout, AlbumDetailFragment.newInstance())
            .commit()
    }
}
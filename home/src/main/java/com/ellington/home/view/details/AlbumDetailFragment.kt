package com.ellington.home.view.details

import android.content.Context
import com.ellington.home.R
import com.ellington.home.dagger.inject
import com.ellington.home.view.AlbumViewingFragment

class AlbumDetailFragment(override val layoutResourceId: Int = R.layout.fragment_album_detail) :
    AlbumViewingFragment() {

    override fun onAttach(context: Context) {
        inject(this)
        super.onAttach(context)
    }

    override fun observeLiveData() {
    }
}
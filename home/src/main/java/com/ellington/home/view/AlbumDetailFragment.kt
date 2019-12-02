package com.ellington.home.view

import android.os.Bundle
import android.view.View
import com.ellington.home.R
import com.ellington.home.viewmodel.AlbumListViewModel
import com.ellington.mvvm.viewmodel.ViewModelFragment

class AlbumDetailFragment(override val layoutResourceId: Int = R.layout.fragment_album_detail) :
    ViewModelFragment<AlbumListViewModel>() {


    companion object {
        fun newInstance(): AlbumDetailFragment {
            return AlbumDetailFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun observeLiveData() {

    }
}
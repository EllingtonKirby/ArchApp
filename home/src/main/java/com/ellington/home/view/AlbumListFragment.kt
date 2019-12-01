package com.ellington.home.view

import android.content.Context
import com.ellington.home.dagger.inject
import com.ellington.home.viewmodel.AlbumListViewModel
import com.ellington.mvvm.viewmodel.ViewModelFragment

class AlbumListFragment(override val layoutResourceId: Int) :
    ViewModelFragment<AlbumListViewModel>() {

    override fun onAttach(context: Context) {
        inject(this)
        super.onAttach(context)
    }

    override fun observeLiveData() {
    }
}
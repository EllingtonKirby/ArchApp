package com.ellington.home.view

import android.content.Context
import com.ellington.home.dagger.inject
import com.ellington.home.viewmodel.PictureListViewModel
import com.ellington.mvvm.viewmodel.ViewModelFragment

class PictureListFragment(override val layoutResourceId: Int) :
    ViewModelFragment<PictureListViewModel>() {

    override fun onAttach(context: Context?) {
        inject(this)
        super.onAttach(context)
    }

    override fun observeLiveData() {

    }
}
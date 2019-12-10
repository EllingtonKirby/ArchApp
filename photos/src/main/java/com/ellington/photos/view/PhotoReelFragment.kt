package com.ellington.photos.view

import android.content.Context
import com.ellington.mvvm.viewmodel.ViewModelFragment
import com.ellington.photos.R
import com.ellington.photos.dagger.inject
import com.ellington.photos.viewmodel.PhotoReelViewModel

class PhotoReelFragment(override val layoutResourceId: Int = R.layout.fragment_photo_reel) :
    ViewModelFragment<PhotoReelViewModel>() {

    companion object {
        fun newInstance(): PhotoReelFragment {
            return PhotoReelFragment()
        }
    }

    override fun onAttach(context: Context) {
        inject(this)
        super.onAttach(context)
    }

    override fun observeLiveData() {
    }
}
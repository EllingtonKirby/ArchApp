package com.ellington.photos.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ellington.mvvm.viewmodel.ViewModelFragment
import com.ellington.photos.R
import com.ellington.photos.dagger.inject
import com.ellington.photos.viewmodel.PhotoReelViewModel
import kotlinx.android.synthetic.main.fragment_photo_reel.*

class PhotoReelFragment(override val layoutResourceId: Int = R.layout.fragment_photo_reel) :
    ViewModelFragment<PhotoReelViewModel>() {

    private lateinit var adapter: PhotoReelAdapter

    companion object {
        fun newInstance(): PhotoReelFragment {
            return PhotoReelFragment()
        }
    }

    override fun onAttach(context: Context) {
        inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        viewModel.getRandomUsers(40)
    }

    private fun setUpAdapter() {
        adapter = PhotoReelAdapter(viewModel)
        photo_reel_recycler.layoutManager = GridLayoutManager(context, 4)
        photo_reel_recycler.adapter = adapter
    }

    override fun observeLiveData() {
        viewModel.randomUsersResponse.observe(this, Observer {
            adapter.setData(it.results)
        })
    }
}
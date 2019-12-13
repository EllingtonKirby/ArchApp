package com.ellington.photos.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ellington.mvvm.utils.EndlessRecyclerViewScrollListener
import com.ellington.mvvm.viewmodel.ViewModelFragment
import com.ellington.photos.R
import com.ellington.photos.dagger.inject
import com.ellington.photos.viewmodel.PhotoReelViewModel
import kotlinx.android.synthetic.main.fragment_photo_reel.*

class PhotoReelFragment(override val layoutResourceId: Int = R.layout.fragment_photo_reel) :
    ViewModelFragment<PhotoReelViewModel>() {

    private lateinit var adapter: PhotoReelAdapter

    companion object {
        const val NUMBER_OF_RESPONSES = 500
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
        viewModel.getRandomUsers(NUMBER_OF_RESPONSES)
    }

    private fun setUpAdapter() {
        adapter = PhotoReelAdapter(viewModel)
        val layoutManager = GridLayoutManager(context, 4).apply {
            photo_reel_recycler.layoutManager = this
        }
        photo_reel_recycler.adapter = adapter
        photo_reel_recycler.addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.loadMoreRandomUsers()
            }
        })
    }

    override fun observeLiveData() {
        viewModel.randomUsersResponse.observe(this, Observer {
            //This is hacky, the proper replacement would be to implement the DiffUtil for RandomPerson
            adapter.notifyItemRangeInserted(adapter.itemCount, it.size)
        })

        viewModel.loading.observe(this, Observer {
            photo_reel_progress.visibility = if (it) View.VISIBLE else View.GONE
        })
    }
}
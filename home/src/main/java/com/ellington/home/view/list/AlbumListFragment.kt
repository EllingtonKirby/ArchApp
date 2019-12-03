package com.ellington.home.view.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ellington.home.R
import com.ellington.home.dagger.inject
import com.ellington.home.viewmodel.AlbumListViewModel
import com.ellington.mvvm.utils.EndlessRecyclerViewScrollListener
import com.ellington.mvvm.viewmodel.ViewModelFragment
import kotlinx.android.synthetic.main.fragment_album_list.*

class AlbumListFragment(override val layoutResourceId: Int = R.layout.fragment_album_list) :
    ViewModelFragment<AlbumListViewModel>() {

    private lateinit var adapter: AlbumListGridAdapter

    override fun onAttach(context: Context) {
        inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
    }

    private fun setUpAdapter() {
        val layoutManager = GridLayoutManager(context, 2)
        adapter = AlbumListGridAdapter(viewModel)

        album_list_recycler.layoutManager = layoutManager
        album_list_recycler.adapter = adapter

        setUpEndlessScroll(layoutManager)
    }

    private fun setUpEndlessScroll(layoutManager: GridLayoutManager) {
        album_list_recycler.addOnScrollListener(
            object : EndlessRecyclerViewScrollListener(layoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    viewModel.loadNextPageOfAlbums()
                }
            }
        )
    }

    override fun observeLiveData() {
        viewModel.albumList.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })

        viewModel.openAlbum.observe(this, Observer {
            val id = it.getContentIfNotHandled()
            id?.let {
                val direction =
                    AlbumListFragmentDirections.actionAlbumListFragmentToAlbumDetailFragment(
                        id
                    )
                findNavController().navigate(direction)
            }
        })

        viewModel.isLoading.observe(this, Observer {
            album_list_progress.visibility = if (it) View.VISIBLE else View.GONE
        })
    }
}
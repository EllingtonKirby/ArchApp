package com.ellington.home.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ellington.home.R
import com.ellington.home.dagger.inject
import com.ellington.home.viewmodel.AlbumListViewModel
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
        val layoutManager = GridLayoutManager(context, 4)
        adapter = AlbumListGridAdapter(viewModel)

        album_list_recycler.layoutManager = layoutManager
        album_list_recycler.adapter = adapter
    }

    override fun observeLiveData() {
        viewModel.albumList.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })
    }
}
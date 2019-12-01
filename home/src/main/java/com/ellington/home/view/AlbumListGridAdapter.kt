package com.ellington.home.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ellington.home.data.Album
import com.ellington.home.viewmodel.AlbumListViewModel

class AlbumListGridAdapter(private val albumListViewModel: AlbumListViewModel) :
    RecyclerView.Adapter<AlbumListGridAdapter.ViewHolder>() {

    class ViewHolder private constructor(private val view: AlbumItemView) :
        RecyclerView.ViewHolder(view) {

        fun bind(viewModel: AlbumListViewModel, album: Album) {
            view.bind(viewModel, album)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val createdView = AlbumItemView(parent.context)
                return ViewHolder(createdView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return albumListViewModel.getAlbumListSize()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albumListViewModel, albumListViewModel.getAlbumAtPosition(position))
    }
}
package com.ellington.photos.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ellington.imageloading.ImageLoader
import com.ellington.imageloading.impl.ImageLoaderImpl
import com.ellington.photos.viewmodel.PhotoReelViewModel

class PhotoReelAdapter(private val viewModel: PhotoReelViewModel) :
    RecyclerView.Adapter<PhotoReelAdapter.ViewHolder>() {

    private val imageLoader: ImageLoader = ImageLoaderImpl()

    class ViewHolder(val view: PhotoReelItemView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PhotoReelItemView(parent.context, imageLoader))
    }

    override fun getItemCount(): Int {
        return viewModel.getRandomUsersCount() ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        viewModel.getUseAtPosition(position)?.let {
            holder.view.bind(it)
        }
    }
}
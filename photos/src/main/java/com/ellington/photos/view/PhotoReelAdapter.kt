package com.ellington.photos.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ellington.photos.data.RandomUser
import com.ellington.photos.viewmodel.PhotoReelViewModel

class PhotoReelAdapter(private val viewModel: PhotoReelViewModel) :
    RecyclerView.Adapter<PhotoReelAdapter.ViewHolder>() {

    class ViewHolder(private val view: PhotoReelItemView) : RecyclerView.ViewHolder(view) {
        fun bind(data: RandomUser) {
            view.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PhotoReelItemView(parent.context))
    }

    override fun getItemCount(): Int {
        return viewModel.getRandomUsersCount() ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        viewModel.getUseAtPosition(position)?.let {
            holder.bind(it)
        }
    }
}
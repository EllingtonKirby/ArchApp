package com.ellington.photos.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ellington.photos.data.RandomUser
import com.ellington.photos.viewmodel.PhotoReelViewModel

class PhotoReelAdapter(val viewModel: PhotoReelViewModel) :
    RecyclerView.Adapter<PhotoReelAdapter.ViewHolder>() {

    private val data: MutableList<RandomUser> = mutableListOf()

    class ViewHolder(private val view: PhotoReelItemView) : RecyclerView.ViewHolder(view) {
        fun bind(data: RandomUser) {
            view.bind(data)
        }
    }

    fun setData(newData: List<RandomUser>) {
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PhotoReelItemView(parent.context))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data[position].let {
            holder.bind(it)
        }
    }
}
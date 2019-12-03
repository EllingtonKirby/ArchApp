package com.ellington.home.view.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ellington.home.R
import com.ellington.home.data.TrackList

class TrackListAdapter :
    RecyclerView.Adapter<TrackListAdapter.ViewHolder>() {

    var data: TrackList? = null

    class ViewHolder constructor(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.album_info_track_list_track, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data?.data?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.track_list_name)?.let {
            it.text = data?.data?.get(position)?.title
        }
    }
}
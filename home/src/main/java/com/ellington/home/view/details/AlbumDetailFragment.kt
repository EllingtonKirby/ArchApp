package com.ellington.home.view.details

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ellington.home.R
import com.ellington.home.dagger.GlideApp
import com.ellington.home.dagger.inject
import com.ellington.home.data.Album
import com.ellington.home.view.AlbumViewingFragment
import kotlinx.android.synthetic.main.fragment_album_detail.*
import kotlinx.android.synthetic.main.layout_album_info.*

class AlbumDetailFragment(override val layoutResourceId: Int = R.layout.fragment_album_detail) :
    AlbumViewingFragment() {

    override fun onAttach(context: Context) {
        inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.let { AlbumDetailFragmentArgs.fromBundle(it) }
        viewModel.loadAlbumById(args?.albumId)
    }

    override fun observeLiveData() {
        viewModel.singleAlbum.observe(this, Observer {
            setUpViews(it)
        })
    }

    private fun setUpViews(album: Album) {
        GlideApp.with(this)
            .load(album.coverXl)
            .fitCenter()
            .placeholder(R.drawable.ic_album_placeholder)
            .error(R.drawable.ic_album_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade(300))
            .into(album_detail_album_image)

        album_info_artist.append(album.artist.name)

        GlideApp.with(this)
            .load(album.artist.pictureMedium)
            .fitCenter()
            .placeholder(R.drawable.ic_album_placeholder)
            .error(R.drawable.ic_album_placeholder)
            .circleCrop()
            .transition(DrawableTransitionOptions.withCrossFade(300))
            .into(album_info_artist_image)

        album_info_release_date.append(album.releaseDate)
    }
}
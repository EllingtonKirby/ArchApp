package com.ellington.home.view.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ellington.home.R
import com.ellington.home.dagger.GlideApp
import com.ellington.home.dagger.addCommonPlaceHolderAndFade
import com.ellington.home.dagger.inject
import com.ellington.home.data.Album
import com.ellington.home.viewmodel.details.AlbumDetailsViewModel
import com.ellington.mvvm.viewmodel.ViewModelFragment
import kotlinx.android.synthetic.main.fragment_album_detail.*
import kotlinx.android.synthetic.main.layout_album_info.*

class AlbumDetailFragment(override val layoutResourceId: Int = R.layout.fragment_album_detail) :
    ViewModelFragment<AlbumDetailsViewModel>() {

    private lateinit var adapter: TrackListAdapter

    override fun onAttach(context: Context) {
        inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.let { AlbumDetailFragmentArgs.fromBundle(it) }
        viewModel.loadAlbumById(args?.albumId)

        adapter = TrackListAdapter()
        album_info_track_list.isNestedScrollingEnabled = false
        album_info_track_list.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        album_info_track_list.adapter = adapter
    }

    override fun observeLiveData() {
        viewModel.singleAlbum.observe(this, Observer {
            setUpViews(it)
        })

        viewModel.trackList.observe(this, Observer {
            adapter.data = it
            adapter.notifyDataSetChanged()
        })

        viewModel.isLoading.observe(this, Observer {
            album_info_progress.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun setUpViews(album: Album) {

        GlideApp.with(this)
            .load(album.coverXl)
            .addCommonPlaceHolderAndFade()
            .into(album_detail_album_image)

        album_info_artist.append(album.artist.name)

        GlideApp.with(this)
            .load(album.artist.pictureMedium)
            .addCommonPlaceHolderAndFade()
            .circleCrop()
            .into(album_info_artist_image)

        album_info_release_date.append(album.releaseDate)

        album_info_fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(album.link)
            startActivity(intent)
        }
    }
}
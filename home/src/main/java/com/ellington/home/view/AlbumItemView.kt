package com.ellington.home.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ellington.home.R
import com.ellington.home.dagger.GlideApp
import com.ellington.home.data.Album
import com.ellington.home.viewmodel.AlbumListViewModel
import com.ellington.mvvm.base.BaseView
import kotlinx.android.synthetic.main.item_view_album.view.*

class AlbumItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), BaseView<AlbumListViewModel, Album> {

    override val layoutResourceId: Int
        get() = R.layout.item_view_album

    init {
        inflate(context, layoutResourceId, this)
    }

    override fun showProgress() {
    }

    override fun bind(viewModel: AlbumListViewModel, data: Album) {
        GlideApp.with(this)
            .load(data.coverMedium)
            .fitCenter()
            .placeholder(R.drawable.ic_album_placeholder)
            .error(R.drawable.ic_album_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade(300))
            .into(album_item_image)
    }
}
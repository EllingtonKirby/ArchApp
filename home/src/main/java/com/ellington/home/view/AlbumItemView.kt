package com.ellington.home.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.ellington.home.data.Album
import com.ellington.home.viewmodel.AlbumListViewModel
import com.ellington.mvvm.base.BaseView

class AlbumItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), BaseView<AlbumListViewModel, Album> {

    override fun showProgress() {
    }

    override fun bind(viewModel: AlbumListViewModel, data: Album) {
    }
}
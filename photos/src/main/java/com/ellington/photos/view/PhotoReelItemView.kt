package com.ellington.photos.view

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.ellington.imageloading.ImageLoader
import com.ellington.photos.R
import com.ellington.photos.data.RandomUser
import kotlinx.android.synthetic.main.item_photo_reel.view.*

class PhotoReelItemView(context: Context?, private val imageLoader: ImageLoader) :
    ConstraintLayout(context) {

    private var randomUser: RandomUser? = null

    init {
        inflate(context, R.layout.item_photo_reel, this)
    }

    fun bind(data: RandomUser) {
        randomUser = data
        imageLoader.loadImageFromUrl(
            randomUser?.getMediumPictureUrl() ?: return,
            photo_reel_item_image
        )
    }
}
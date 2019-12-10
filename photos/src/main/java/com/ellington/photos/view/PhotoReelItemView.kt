package com.ellington.photos.view

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.ellington.photos.R
import com.ellington.photos.data.RandomUser

class PhotoReelItemView(context: Context?) : ConstraintLayout(context) {

    init {
        inflate(context, R.layout.item_photo_reel, this)
    }

    fun bind(data: RandomUser) {
    }
}
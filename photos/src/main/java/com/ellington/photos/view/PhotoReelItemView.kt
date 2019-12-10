package com.ellington.photos.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import com.ellington.photos.R
import com.ellington.photos.data.RandomUser
import kotlinx.android.synthetic.main.item_photo_reel.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class PhotoReelItemView(context: Context?) : ConstraintLayout(context) {

    private val bitmapData = MutableLiveData<Bitmap>()

    init {
        inflate(context, R.layout.item_photo_reel, this)

        bitmapData.observeForever {
            photo_reel_item_image.setImageBitmap(it)
        }
    }

    fun bind(data: RandomUser) {
        CoroutineScope(Dispatchers.IO).launch {
            val bitmap = loadImage(data.getThumbnailUrl())
            bitmapData.postValue(bitmap)
        }
    }

    private fun loadImage(url: String?): Bitmap {
        val parsed = URL(url)
        return BitmapFactory.decodeStream(parsed.openStream())
    }
}
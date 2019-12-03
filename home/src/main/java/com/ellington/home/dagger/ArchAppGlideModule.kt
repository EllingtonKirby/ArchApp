package com.ellington.home.dagger

import android.graphics.drawable.Drawable
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.module.AppGlideModule
import com.ellington.home.R

@GlideModule
public class ArchAppGlideModule : AppGlideModule()

fun GlideRequest<Drawable>.addCommonPlaceHolderAndFade(): GlideRequest<Drawable> {
    return this.fitCenter()
        .placeholder(R.drawable.ic_album_placeholder)
        .error(R.drawable.ic_album_placeholder)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .transition(DrawableTransitionOptions.withCrossFade(300))
}
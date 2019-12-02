package com.ellington.home.view

import com.ellington.mvvm.utils.Event

interface HomeNavigationListener {
    fun onAlbumSelected(event: Event<String>)
}
package com.ellington.navigation

import android.content.Intent

interface DynamicFeature<T> {
    val dynamicStart: T?
}

object HomeNavigation : DynamicFeature<Intent> {

    private const val HOME = "com.ellington.home.view.HomeActivity"

    override val dynamicStart: Intent?
        get() = HOME.loadIntentOrNull()
}
package com.ellington.architecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ellington.navigation.HomeNavigation

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(HomeNavigation.dynamicStart)
    }
}

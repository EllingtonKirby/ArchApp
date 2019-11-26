package com.ellington.home.view

import android.os.Bundle
import com.ellington.home.R
import com.ellington.mvvm.base.BaseActivity

class HomeActivity(override val layoutResourceId: Int = R.layout.activity_home) : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
    }
}
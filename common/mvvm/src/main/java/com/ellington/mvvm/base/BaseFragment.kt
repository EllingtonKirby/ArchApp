package com.ellington.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResourceId, container, false)
    }

    fun View.showSnackbar(
        s: String,
        len: Int = Snackbar.LENGTH_LONG,
        f: Snackbar.() -> Unit
    ) {
        val snack = Snackbar.make(this, s, len)
        snack.f()
        snack.show()
    }
}
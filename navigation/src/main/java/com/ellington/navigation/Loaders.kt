package com.ellington.navigation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

private val classMap = mutableMapOf<String, Class<*>>()
private const val PACKAGE_NAME = "com.ellington.architecture"

internal fun String.loadFragmentOrNull(): Fragment? =
    try {
        this.loadClassOrNull<Fragment>()?.newInstance()
    } catch (e: ClassNotFoundException) {
        null
    }

private inline fun <reified T : Any> Any.castOrNull() = this as? T

internal fun <T> String.loadClassOrNull(): Class<out T>? =
    classMap.getOrPut(this) {
        try {
            Class.forName(this)
        } catch (e: ClassNotFoundException) {
            return null
        }
    }.castOrNull()


private fun intentTo(className: String): Intent =
    Intent(Intent.ACTION_VIEW).setClassName(PACKAGE_NAME, className)

internal fun String.loadIntentOrNull(): Intent? =
    try {
        Class.forName(this).run { intentTo(this@loadIntentOrNull) }
    } catch (e: ClassNotFoundException) {
        null
    }

object SplitInstall {
    fun download(c: Context, dynamicModule: String, installedAction: () -> Unit) {
        val manager = SplitInstallManagerFactory.create(c)
        val request = SplitInstallRequest.newBuilder()
            .addModule(dynamicModule)
            .build()
        manager.registerListener {
            if (it.status() == SplitInstallSessionStatus.INSTALLED) installedAction.invoke()
        }
        manager.startInstall(request)
    }
}
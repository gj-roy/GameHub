@file:JvmName("WindowInsetsUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.annotation.SuppressLint
import android.view.WindowInsets
import ca.on.hojat.gamenews.shared.commons.core.SdkInfo

@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun WindowInsets.getCompatSystemWindowInsetLeft(): Int {
    return if (SdkInfo.IS_AT_LEAST_11) {
        getInsets(WindowInsets.Type.systemBars()).left
    } else {
        systemWindowInsetLeft
    }
}

@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun WindowInsets.getCompatSystemWindowInsetTop(): Int {
    return if (SdkInfo.IS_AT_LEAST_11) {
        getInsets(WindowInsets.Type.systemBars()).top
    } else {
        systemWindowInsetTop
    }
}

@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun WindowInsets.getCompatSystemWindowInsetRight(): Int {
    return if (SdkInfo.IS_AT_LEAST_11) {
        getInsets(WindowInsets.Type.systemBars()).right
    } else {
        systemWindowInsetRight
    }
}

@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun WindowInsets.getCompatSystemWindowInsetBottom(): Int {
    return if (SdkInfo.IS_AT_LEAST_11) {
        getInsets(WindowInsets.Type.systemBars()).bottom
    } else {
        systemWindowInsetBottom
    }
}

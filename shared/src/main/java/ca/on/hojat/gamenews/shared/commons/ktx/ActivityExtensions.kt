@file:JvmName("ActivityUtils")

package ca.on.hojat.gamenews.shared.commons.ktx

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.graphics.BitmapFactory
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import ca.on.hojat.gamenews.shared.commons.core.SdkInfo

@get:ColorInt
var Activity.statusBarColor: Int
    set(@ColorInt value) {
        window.statusBarColor = value
    }
    get() = window.statusBarColor

@get:ColorInt
var Activity.navigationBarColor: Int
    set(@ColorInt value) {
        window.navigationBarColor = value
    }
    get() = window.navigationBarColor

fun Activity.makeScreenAlwaysAwake() {
    window.makeScreenAlwaysAwake()
}

fun Activity.makeScreenSleepable() {
    window.makeScreenSleepable()
}

fun Activity.setScreenAlwaysAwake(isScreenAlwaysAwake: Boolean) {
    window.setScreenAlwaysAwake(isScreenAlwaysAwake)
}

fun Activity.setSoftInputMode(mode: Int) {
    window.setSoftInputMode(mode)
}

@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun Activity.setTaskDescription(
    label: String,
    @DrawableRes iconId: Int,
    @ColorInt primaryColor: Int
) {
    setTaskDescription(
        if (SdkInfo.IS_AT_LEAST_PIE) {
            ActivityManager.TaskDescription(label, iconId, primaryColor)
        } else {
            ActivityManager.TaskDescription(
                label,
                BitmapFactory.decodeResource(resources, iconId),
                primaryColor
            )
        }
    )
}

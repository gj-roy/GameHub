@file:JvmName("CanvasUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.Region
import ca.on.hojat.gamenews.shared.commons.core.SdkInfo

@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun Canvas.clipOutPathCompat(path: Path) {
    if (SdkInfo.IS_AT_LEAST_OREO) {
        clipOutPath(path)
    } else {
        clipPath(path, Region.Op.DIFFERENCE)
    }
}

@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun Canvas.clipPathCompat(path: Path) {
    if (SdkInfo.IS_AT_LEAST_OREO) {
        clipPath(path)
    } else {
        clipPath(path, Region.Op.INTERSECT)
    }
}

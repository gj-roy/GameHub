@file:JvmName("PathUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.graphics.Path
import android.graphics.PointF

fun Path.moveTo(point: PointF) {
    moveTo(point.x, point.y)
}

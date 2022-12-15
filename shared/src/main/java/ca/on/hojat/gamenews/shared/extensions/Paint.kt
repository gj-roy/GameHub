@file:JvmName("FontMetricsUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.graphics.Paint
import android.graphics.Rect

val Paint.FontMetrics.recommendedHeight: Float
    get() = (descent - ascent)

val Paint.FontMetrics.maximumHeight: Float
    get() = (bottom - top)

fun Paint.getTextBounds(text: String, rect: Rect) {
    getTextBounds(text, 0, text.length, rect)
}

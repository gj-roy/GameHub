@file:JvmName("FontMetricsUtils")

package ca.on.hojat.gamenews.shared.commons.ktx.drawing

import android.graphics.Paint

val Paint.FontMetrics.recommendedHeight: Float
    get() = (descent - ascent)

val Paint.FontMetrics.maximumHeight: Float
    get() = (bottom - top)

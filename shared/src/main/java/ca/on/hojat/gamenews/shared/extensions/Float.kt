package ca.on.hojat.gamenews.shared.extensions

import android.content.Context

fun Float.dpToPx(context: Context): Float {
    return (this * context.displayMetrics.density)
}


fun Float.spToPx(context: Context): Float {
    return (this * context.displayMetrics.scaledDensity)
}


fun Float.pxToDp(context: Context): Float {
    return (this / context.displayMetrics.density)
}


fun Float.pxToSp(context: Context): Float {
    return (this / context.displayMetrics.scaledDensity)
}



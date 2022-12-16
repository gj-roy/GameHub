package ca.on.hojat.gamenews.shared.extensions

import android.content.Context

fun Float.pxToDp(context: Context): Float {
    return (this / context.displayMetrics.density)
}

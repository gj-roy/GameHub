package ca.on.hojat.gamenews.shared.extensions

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.annotation.ColorInt

private const val COLOR_ALPHA_MAX = 255

val Int.isOpaque: Boolean
    get() = (Color.alpha(this) == COLOR_ALPHA_MAX)

fun Int.containsBits(bits: Int): Boolean {
    return ((this and bits) == bits)
}

fun @receiver:ColorInt Int.toColorStateList(): ColorStateList {
    return ColorStateList.valueOf(this)
}

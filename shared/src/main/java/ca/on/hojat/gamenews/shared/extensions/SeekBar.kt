@file:JvmName("SeekBarUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.widget.SeekBar
import androidx.annotation.ColorInt

fun SeekBar.setThumbColor(@ColorInt color: Int) {
    thumb.setColor(color)
}

fun SeekBar.setPrimaryProgressColor(@ColorInt color: Int) {
    progressTintList = color.toColorStateList()
}

fun SeekBar.setSecondaryProgressColor(@ColorInt color: Int) {
    progressBackgroundTintList = color.toColorStateList()
}

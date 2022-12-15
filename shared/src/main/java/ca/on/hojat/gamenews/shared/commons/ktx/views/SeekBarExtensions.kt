@file:JvmName("SeekBarUtils")

package ca.on.hojat.gamenews.shared.commons.ktx.views

import android.widget.SeekBar
import androidx.annotation.ColorInt
import ca.on.hojat.gamenews.shared.commons.ktx.setColor
import ca.on.hojat.gamenews.shared.commons.ktx.toColorStateList

fun SeekBar.setThumbColor(@ColorInt color: Int) {
    thumb.setColor(color)
}

fun SeekBar.setPrimaryProgressColor(@ColorInt color: Int) {
    progressTintList = color.toColorStateList()
}

fun SeekBar.setSecondaryProgressColor(@ColorInt color: Int) {
    progressBackgroundTintList = color.toColorStateList()
}

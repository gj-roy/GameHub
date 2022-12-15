@file:JvmName("ProgressBarUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.widget.ProgressBar
import androidx.annotation.ColorInt

fun ProgressBar.setColor(@ColorInt color: Int) {
    indeterminateDrawable = indeterminateDrawable?.setColor(color)
}

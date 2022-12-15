@file:JvmName("ProgressBarUtils")

package ca.on.hojat.gamenews.shared.commons.ktx.views

import android.widget.ProgressBar
import androidx.annotation.ColorInt
import ca.on.hojat.gamenews.shared.commons.ktx.setColor

fun ProgressBar.setColor(@ColorInt color: Int) {
    indeterminateDrawable = indeterminateDrawable?.setColor(color)
}

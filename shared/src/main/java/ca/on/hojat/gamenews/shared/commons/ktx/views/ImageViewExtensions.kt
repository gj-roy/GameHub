@file:JvmName("ImageViewUtils")

package ca.on.hojat.gamenews.shared.commons.ktx.views

import android.widget.ImageView
import androidx.annotation.ColorInt
import ca.on.hojat.gamenews.shared.commons.ktx.setColor

fun ImageView.setColor(@ColorInt color: Int) {
    if (drawable != null) {
        setImageDrawable(drawable?.setColor(color))
    }
}

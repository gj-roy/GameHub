@file:JvmName("ImageViewUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.widget.ImageView
import androidx.annotation.ColorInt

fun ImageView.setColor(@ColorInt color: Int) {
    if (drawable != null) {
        setImageDrawable(drawable?.setColor(color))
    }
}

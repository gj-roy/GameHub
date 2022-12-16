@file:JvmName("DrawableUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import androidx.annotation.ColorInt

fun Drawable.setColor(@ColorInt color: Int): Drawable {
    return when (val newDrawable = mutate()) {
        is RippleDrawable -> newDrawable.apply { setColor(color.toColorStateList()) }
        is GradientDrawable -> newDrawable.apply { setColor(color) }

        else -> newDrawable.apply {
            colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }
}


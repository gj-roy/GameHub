@file:JvmName("DrawableUtils")

package ca.on.hojat.gamenews.core.extensions

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import androidx.annotation.ColorInt

fun Drawable.setColor(@ColorInt color: Int): Drawable {
    return when (val newDrawable = mutate()) {
        is RippleDrawable -> newDrawable.apply {
            setColor(
                ColorStateList.valueOf(color)
            )
        }
        is GradientDrawable -> newDrawable.apply { setColor(color) }

        else -> newDrawable.apply {
            colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }
}


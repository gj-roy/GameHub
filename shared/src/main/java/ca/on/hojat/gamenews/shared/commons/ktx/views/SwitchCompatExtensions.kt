@file:JvmName("SwitchCompatUtils")

package ca.on.hojat.gamenews.shared.commons.ktx.views

import android.content.res.ColorStateList
import androidx.annotation.ColorInt
import androidx.appcompat.widget.SwitchCompat
import androidx.core.graphics.drawable.DrawableCompat
import ca.on.hojat.gamenews.shared.commons.ktx.withHalfAlpha

fun SwitchCompat.setColor(@ColorInt color: Int) {
    setColors(color, color, color, color)
}

fun SwitchCompat.setColors(
    @ColorInt deactivatedPointerColor: Int,
    @ColorInt activatedPointerColor: Int,
    @ColorInt deactivatedBackgroundColor: Int,
    @ColorInt activatedBackgroundColor: Int
) {
    val switchStates: Array<IntArray> = arrayOf(
        intArrayOf(-android.R.attr.state_checked),
        intArrayOf(android.R.attr.state_checked)
    )

    val switchThumbDrawableColors = intArrayOf(
        deactivatedPointerColor,
        activatedPointerColor
    )

    val switchTrackDrawableColors = intArrayOf(
        deactivatedBackgroundColor.withHalfAlpha(),
        activatedBackgroundColor.withHalfAlpha()
    )

    DrawableCompat.setTintList(
        DrawableCompat.wrap(thumbDrawable),
        ColorStateList(switchStates, switchThumbDrawableColors)
    )

    DrawableCompat.setTintList(
        DrawableCompat.wrap(trackDrawable),
        ColorStateList(switchStates, switchTrackDrawableColors)
    )
}

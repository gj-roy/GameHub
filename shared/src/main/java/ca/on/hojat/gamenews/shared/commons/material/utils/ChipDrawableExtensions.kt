@file:JvmName("ChipDrawableUtils")

package ca.on.hojat.gamenews.shared.commons.material.utils

import androidx.annotation.ColorInt
import com.google.android.material.chip.ChipDrawable
import ca.on.hojat.gamenews.shared.extensions.toColorStateList

fun ChipDrawable.setChipBackgroundColor(@ColorInt color: Int) {
    chipBackgroundColor = color.toColorStateList()
}

fun ChipDrawable.setChipIconColor(@ColorInt color: Int) {
    chipIconTint = color.toColorStateList()
}

fun ChipDrawable.setChipBackgroundCornerRadius(radius: Float) {
    shapeAppearanceModel = shapeAppearanceModel.withCornerSize(radius)
}

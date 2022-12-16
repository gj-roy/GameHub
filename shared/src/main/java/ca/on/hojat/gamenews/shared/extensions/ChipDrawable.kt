package ca.on.hojat.gamenews.shared.extensions

import androidx.annotation.ColorInt
import com.google.android.material.chip.ChipDrawable


fun ChipDrawable.setChipBackgroundColor(@ColorInt color: Int) {
    chipBackgroundColor = color.toColorStateList()
}

fun ChipDrawable.setChipIconColor(@ColorInt color: Int) {
    chipIconTint = color.toColorStateList()
}

fun ChipDrawable.setChipBackgroundCornerRadius(radius: Float) {
    shapeAppearanceModel = shapeAppearanceModel.withCornerSize(radius)
}

@file:JvmName("CheckBoxUtils")

package ca.on.hojat.gamenews.shared.commons.ktx.views

import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.widget.CompoundButtonCompat
import ca.on.hojat.gamenews.shared.commons.ktx.toColorStateList

fun AppCompatCheckBox.setColor(@ColorInt color: Int) {
    CompoundButtonCompat.setButtonTintList(this, color.toColorStateList())
}

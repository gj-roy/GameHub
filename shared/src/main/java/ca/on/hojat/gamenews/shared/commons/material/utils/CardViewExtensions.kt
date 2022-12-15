@file:JvmName("CardViewUtils")

package ca.on.hojat.gamenews.shared.commons.material.utils

import androidx.annotation.ColorInt
import com.google.android.material.card.MaterialCardView
import ca.on.hojat.gamenews.shared.extensions.toColorStateList

fun MaterialCardView.setRippleColor(@ColorInt color: Int) {
    rippleColor = color.toColorStateList()
}

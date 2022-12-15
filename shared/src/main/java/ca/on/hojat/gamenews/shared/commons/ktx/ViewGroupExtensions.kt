@file:JvmName("ViewGroupUtils")

package ca.on.hojat.gamenews.shared.commons.ktx

import android.view.ViewGroup
import androidx.annotation.Px

val ViewGroup.MarginLayoutParams.horizontalMargin: Int
    get() = (marginStart + marginEnd)

val ViewGroup.MarginLayoutParams.verticalMargin: Int
    get() = (topMargin + bottomMargin)

fun ViewGroup.MarginLayoutParams.setHorizontalMargin(@Px margin: Int) {
    marginStart = margin
    marginEnd = margin
}

fun ViewGroup.MarginLayoutParams.setVerticalMargin(@Px margin: Int) {
    topMargin = margin
    bottomMargin = margin
}

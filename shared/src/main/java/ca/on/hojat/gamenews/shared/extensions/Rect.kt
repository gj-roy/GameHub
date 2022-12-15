@file:JvmName("RectUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.graphics.Rect

val Rect.halfWidth: Int
    get() = (width() / 2)

val Rect.halfHeight: Int
    get() = (height() / 2)

fun Rect.setBounds(
    left: Int = this.left,
    top: Int = this.top,
    right: Int = this.right,
    bottom: Int = this.bottom
) {
    set(left, top, right, bottom)
}

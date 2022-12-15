package ca.on.hojat.gamenews.shared.extensions

import android.graphics.RectF

val RectF.halfWidth: Float
    get() = (width() / 2f)

val RectF.halfHeight: Float
    get() = (height() / 2f)

fun RectF.setBounds(
    left: Float = this.left,
    top: Float = this.top,
    right: Float = this.right,
    bottom: Float = this.bottom
) {
    set(left, top, right, bottom)
}

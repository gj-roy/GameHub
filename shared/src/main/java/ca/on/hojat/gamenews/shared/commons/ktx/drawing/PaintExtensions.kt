@file:JvmName("PaintUtils")

package ca.on.hojat.gamenews.shared.commons.ktx.drawing

import android.graphics.Paint
import android.graphics.Rect

fun Paint.getTextBounds(text: String, rect: Rect) {
    getTextBounds(text, 0, text.length, rect)
}

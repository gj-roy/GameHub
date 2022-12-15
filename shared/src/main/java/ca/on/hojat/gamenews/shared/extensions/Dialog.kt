@file:JvmName("DialogUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.app.Dialog
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes

fun Dialog.getCompatColor(@ColorRes colorId: Int): Int {
    return context.getCompatColor(colorId)
}

fun Dialog.getDimensionPixelSize(@DimenRes dimenId: Int): Int {
    return context.getDimensionPixelSize(dimenId)
}

fun Dialog.getDimension(@DimenRes dimenId: Int): Float {
    return context.getDimension(dimenId)
}

fun Dialog.getCompatDrawable(@DrawableRes drawableId: Int): Drawable? {
    return context.getCompatDrawable(drawableId)
}

fun Dialog.getColoredDrawable(@DrawableRes drawableId: Int, @ColorInt color: Int): Drawable? {
    return context.getColoredDrawable(drawableId, color)
}

fun Dialog.showShortToast(message: CharSequence): Toast {
    return context.showShortToast(message)
}

fun Dialog.showLongToast(message: CharSequence): Toast {
    return context.showLongToast(message)
}

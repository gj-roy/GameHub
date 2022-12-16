@file:JvmName("ViewUtils")
@file:Suppress("UNCHECKED_CAST", "TooManyFunctions")

package ca.on.hojat.gamenews.shared.extensions

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.Px
import androidx.annotation.StringRes
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams

val View.hasLayoutParams: Boolean
    get() = (layoutParams != null)

var View.layoutParamsWidth: Int
    set(value) {
        setLayoutParamsSize(width = value)
    }
    get() = (layoutParams?.width ?: 0)

var View.layoutParamsHeight: Int
    set(value) {
        setLayoutParamsSize(height = value)
    }
    get() = (layoutParams?.height ?: 0)

@get:Px
var View.startMargin: Int
    set(@Px value) {
        setMargins(startMargin = value)
    }
    get() = marginStart

@get:Px
var View.topMargin: Int
    set(@Px value) {
        setMargins(topMargin = value)
    }
    get() = marginTop

@get:Px
var View.endMargin: Int
    set(@Px value) {
        setMargins(endMargin = value)
    }
    get() = marginEnd

@get:Px
var View.bottomMargin: Int
    set(@Px value) {
        setMargins(bottomMargin = value)
    }
    get() = marginBottom

@get:Px
var View.topPadding: Int
    set(@Px value) {
        updatePadding(topPadding = value)
    }
    get() = paddingTop

@get:Px
var View.bottomPadding: Int
    set(@Px value) {
        updatePadding(bottomPadding = value)
    }
    get() = paddingBottom

@get:Px
val View.verticalPadding: Int
    get() = (topPadding + bottomPadding)

fun View.setLayoutParamsSize(size: Int) {
    setLayoutParamsSize(width = size, height = size)
}

fun View.setLayoutParamsSize(
    width: Int = layoutParamsWidth,
    height: Int = layoutParamsHeight
) {
    if (!hasLayoutParams) return

    updateLayoutParams {
        this.width = width
        this.height = height
    }
}

fun View.setMargins(
    @Px startMargin: Int = this.startMargin,
    @Px topMargin: Int = this.topMargin,
    @Px endMargin: Int = this.endMargin,
    @Px bottomMargin: Int = this.bottomMargin
) {
    if (layoutParams !is ViewGroup.MarginLayoutParams) {
        return
    }

    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        this.marginStart = startMargin
        this.topMargin = topMargin
        this.marginEnd = endMargin
        this.bottomMargin = bottomMargin
    }
}


fun View.clearEndMargin() {
    endMargin = 0
}


fun View.updatePadding(
    @Px startPadding: Int = this.paddingStart,
    @Px topPadding: Int = this.paddingTop,
    @Px endPadding: Int = this.paddingEnd,
    @Px bottomPadding: Int = this.paddingBottom
) {
    setPaddingRelative(startPadding, topPadding, endPadding, bottomPadding)
}

fun View.setHorizontalPadding(@Px padding: Int) {
    updatePadding(startPadding = padding, endPadding = padding)
}

fun View.getColor(@ColorRes colorId: Int): Int {
    return context.getCompatColor(colorId)
}

fun View.getDimensionPixelSize(@DimenRes dimenId: Int): Int {
    return context.getDimensionPixelSize(dimenId)
}


fun View.getDimension(@DimenRes dimenId: Int): Float {
    return context.getDimension(dimenId)
}

fun View.getString(@StringRes stringId: Int): String {
    return context.getString(stringId)
}

fun View.getString(@StringRes stringId: Int, vararg args: Any): String {
    return context.getString(stringId, *args)
}

fun View.showLongToast(message: CharSequence): Toast {
    return context.showLongToast(message)
}

fun View.onClick(action: (View) -> Unit) {
    setOnClickListener(action)
}


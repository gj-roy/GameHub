@file:JvmName("ViewUtils")
@file:Suppress("UNCHECKED_CAST", "TooManyFunctions")

package ca.on.hojat.gamenews.shared.extensions

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.IntegerRes
import androidx.annotation.LayoutRes
import androidx.annotation.Px
import androidx.annotation.StringRes
import androidx.core.view.children
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import ca.on.hojat.gamenews.shared.commons.core.utils.observeChanges
import kotlin.properties.ReadWriteProperty

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
val View.horizontalMargin: Int
    get() = (startMargin + endMargin)

@get:Px
val View.verticalMargin: Int
    get() = (topMargin + bottomMargin)

@get:Px
var View.startPadding: Int
    set(@Px value) {
        updatePadding(startPadding = value)
    }
    get() = paddingStart

@get:Px
var View.topPadding: Int
    set(@Px value) {
        updatePadding(topPadding = value)
    }
    get() = paddingTop

@get:Px
var View.endPadding: Int
    set(@Px value) {
        updatePadding(endPadding = value)
    }
    get() = paddingEnd

@get:Px
var View.bottomPadding: Int
    set(@Px value) {
        updatePadding(bottomPadding = value)
    }
    get() = paddingBottom

@get:Px
val View.horizontalPadding: Int
    get() = (startPadding + endPadding)

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

fun View.setHorizontalMargin(@Px margin: Int) {
    setMargins(startMargin = margin, endMargin = margin)
}

fun View.setVerticalMargin(@Px margin: Int) {
    setMargins(topMargin = margin, bottomMargin = margin)
}

fun View.clearMargins() {
    setMargins(
        startMargin = 0,
        topMargin = 0,
        endMargin = 0,
        bottomMargin = 0
    )
}

fun View.clearHorizontalMargins() {
    setHorizontalMargin(0)
}

fun View.clearVerticalMargins() {
    setVerticalMargin(0)
}

fun View.clearStartMargin() {
    startMargin = 0
}

fun View.clearTopMargin() {
    topMargin = 0
}

fun View.clearEndMargin() {
    endMargin = 0
}

fun View.clearBottomMargin() {
    bottomMargin = 0
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

fun View.setVerticalPadding(@Px padding: Int) {
    updatePadding(topPadding = padding, bottomPadding = padding)
}

fun View.clearPadding() {
    updatePadding(
        startPadding = 0,
        topPadding = 0,
        endPadding = 0,
        bottomPadding = 0
    )
}

fun View.clearHorizontalPadding() {
    setHorizontalPadding(0)
}

fun View.clearVerticalPadding() {
    setVerticalPadding(0)
}

fun View.clearStartPadding() {
    startPadding = 0
}

fun View.clearTopPadding() {
    topPadding = 0
}

fun View.clearEndPadding() {
    endPadding = 0
}

fun View.clearBottomPadding() {
    bottomPadding = 0
}

fun <T : ViewGroup.LayoutParams> View.toLayoutParams(): T {
    return (layoutParams as T)
}

fun View.getColor(@ColorRes colorId: Int): Int {
    return context.getCompatColor(colorId)
}

fun View.getDimensionPixelSize(@DimenRes dimenId: Int): Int {
    return context.getDimensionPixelSize(dimenId)
}

fun View.getInteger(@IntegerRes intId: Int): Int {
    return context.getInteger(intId)
}

fun View.getDimension(@DimenRes dimenId: Int): Float {
    return context.getDimension(dimenId)
}

fun View.getFloat(@IntegerRes floatId: Int): Float {
    return context.getFloat(floatId)
}

fun View.getFont(@FontRes fontId: Int): Typeface? {
    return context.getFont(fontId)
}

fun View.getString(@StringRes stringId: Int): String {
    return context.getString(stringId)
}

fun View.getString(@StringRes stringId: Int, vararg args: Any): String {
    return context.getString(stringId, *args)
}

fun View.getDrawable(@DrawableRes drawableId: Int): Drawable? {
    return context.getCompatDrawable(drawableId)
}

fun View.getColoredDrawable(@DrawableRes drawableId: Int, @ColorInt color: Int): Drawable? {
    return context.getColoredDrawable(drawableId, color)
}

fun View.getColoredStrokeDrawable(
    @DrawableRes drawableId: Int,
    @ColorInt strokeColor: Int,
    strokeWidth: Int
): Drawable? {
    return context.getCompatDrawable(drawableId)
        ?.run { mutate() as? GradientDrawable }
        ?.apply { setStroke(strokeWidth, strokeColor) }
}

fun View.inflateView(
    @LayoutRes layoutResourceId: Int,
    root: ViewGroup?,
    attachToRoot: Boolean = true
): View {
    return context.inflateView(
        layoutResourceId = layoutResourceId,
        root = root,
        attachToRoot = attachToRoot
    )
}

fun View.showShortToast(message: CharSequence): Toast {
    return context.showShortToast(message)
}

fun View.showLongToast(message: CharSequence): Toast {
    return context.showLongToast(message)
}

fun View.makeVisible() {
    isVisible = true
}

fun View.makeInvisible() {
    isInvisible = true
}

fun View.makeGone() {
    isGone = true
}

fun View.removeElevation() {
    elevation = 0f
}

fun View.onClick(action: (View) -> Unit) {
    setOnClickListener(action)
}

/**
 * Enables the view by setting its [View.isEnabled] property
 * to true and, optionally, changing its alpha.
 *
 * @param changeAlpha Whether to change the alpha of the view.
 * Default is false.
 * @param alpha The new alpha value for the view if [changeAlpha]
 * parameter is true. Default is 0.5f.
 * @param childrenToo Whether to enable children as well
 * Default is false.
 */
fun View.enable(
    changeAlpha: Boolean = false,
    alpha: Float = 1f,
    childrenToo: Boolean = false
) {
    if (!isEnabled) {
        isEnabled = true

        if (changeAlpha) {
            setAlpha(alpha)
        }

        if (childrenToo && (this is ViewGroup)) {
            for (child in children) {
                child.enable(changeAlpha, alpha, childrenToo)
            }
        }
    }
}

/**
 * Disables the view by setting its [View.isEnabled] property
 * to false and, optionally, changing its alpha.
 *
 * @param changeAlpha Whether to change the alpha of the view.
 * Default is false.
 * @param alpha The new alpha value for the view if [changeAlpha]
 * parameter is true. Default is 0.5f.
 * @param childrenToo Whether to disable children as well.
 * Default is false.
 */
fun View.disable(
    changeAlpha: Boolean = false,
    alpha: Float = 0.5f,
    childrenToo: Boolean = false
) {
    if (isEnabled) {
        isEnabled = false

        if (changeAlpha) {
            setAlpha(alpha)
        }

        if (childrenToo && (this is ViewGroup)) {
            for (child in children) {
                child.disable(changeAlpha, alpha, childrenToo)
            }
        }
    }
}

/**
 * Sets the horizontal and vertical scale of the view.
 *
 * @param scale The new scale
 */
fun View.setScale(scale: Float) {
    scaleX = scale
    scaleY = scale
}

fun View.postAction(action: () -> Unit) {
    post(action)
}

fun View.postActionDelayed(timeInMillis: Long, action: () -> Unit) {
    postDelayed(action, timeInMillis)
}

fun <T> View.invalidateOnChange(initialValue: T): ReadWriteProperty<Any, T> {
    return observeChanges(initialValue) { _, _ -> invalidate() }
}

fun <T> View.relayoutOnChange(initialValue: T): ReadWriteProperty<Any, T> {
    return observeChanges(initialValue) { _, _ -> requestLayout() }
}

fun View.detachFromParent() {
    (this.parent as? ViewGroup)?.removeView(this)
}


fun View.applyWindowStartInsetAsMargin() {
    applyWindowInsetsAsMargin(applyStartInset = true)
}

fun View.applyWindowTopInsetAsMargin() {
    applyWindowInsetsAsMargin(applyTopInset = true)
}

fun View.applyWindowEndInsetAsMargin() {
    applyWindowInsetsAsMargin(applyEndInset = true)
}

fun View.applyWindowBottomInsetAsMargin() {
    applyWindowInsetsAsMargin(applyBottomInset = true)
}

fun View.applyWindowInsetsAsMargin(
    applyStartInset: Boolean = false,
    applyTopInset: Boolean = false,
    applyEndInset: Boolean = false,
    applyBottomInset: Boolean = false
) = applyWindowInsets(
    type = DimensionSnapshotType.MARGINS,
    applyStartInset = applyStartInset,
    applyTopInset = applyTopInset,
    applyEndInset = applyEndInset,
    applyBottomInset = applyBottomInset
)

fun View.applyWindowStartInsetAsPadding() {
    applyWindowInsetsAsPadding(applyStartInset = true)
}

fun View.applyWindowTopInsetAsPadding() {
    applyWindowInsetsAsPadding(applyTopInset = true)
}

fun View.applyWindowEndInsetAsPadding() {
    applyWindowInsetsAsPadding(applyEndInset = true)
}

fun View.applyWindowBottomInsetAsPadding() {
    applyWindowInsetsAsPadding(applyBottomInset = true)
}

fun View.applyWindowInsetsAsPadding(
    applyStartInset: Boolean = false,
    applyTopInset: Boolean = false,
    applyEndInset: Boolean = false,
    applyBottomInset: Boolean = false
) = applyWindowInsets(
    type = DimensionSnapshotType.PADDING,
    applyStartInset = applyStartInset,
    applyTopInset = applyTopInset,
    applyEndInset = applyEndInset,
    applyBottomInset = applyBottomInset
)

fun View.applyWindowInsets(
    type: DimensionSnapshotType,
    applyStartInset: Boolean = false,
    applyTopInset: Boolean = false,
    applyEndInset: Boolean = false,
    applyBottomInset: Boolean = false
) {
    doOnApplyWindowInsets(type) { targetView, insets, dimensions ->
        val start =
            (dimensions.start + (if (applyStartInset) insets.getCompatSystemWindowInsetLeft() else 0))
        val top =
            (dimensions.top + (if (applyTopInset) insets.getCompatSystemWindowInsetTop() else 0))
        val end =
            (dimensions.end + (if (applyEndInset) insets.getCompatSystemWindowInsetRight() else 0))
        val bottom =
            (dimensions.bottom + (if (applyBottomInset) insets.getCompatSystemWindowInsetBottom() else 0))

        when (type) {
            DimensionSnapshotType.MARGINS -> targetView.setMargins(start, top, end, bottom)
            DimensionSnapshotType.PADDING -> targetView.updatePadding(start, top, end, bottom)
        }
    }
}

fun View.doOnApplyWindowInsets(
    type: DimensionSnapshotType,
    listener: (View, WindowInsets, DimensionSnapshot) -> Unit
) {
    val dimensionSnapshot = createDimensionSnapshot(type)

    setOnApplyWindowInsetsListener { view, insets ->
        listener(view, insets, dimensionSnapshot)
        insets
    }

    requestApplyInsetsWhenAttached()
}

private fun View.createDimensionSnapshot(type: DimensionSnapshotType): DimensionSnapshot {
    return when (type) {

        DimensionSnapshotType.MARGINS -> DimensionSnapshot(
            start = this.startMargin,
            top = this.topMargin,
            end = this.endMargin,
            bottom = this.bottomMargin
        )

        DimensionSnapshotType.PADDING -> DimensionSnapshot(
            start = this.startPadding,
            top = this.topPadding,
            end = this.endPadding,
            bottom = this.bottomPadding
        )
    }
}

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {

            override fun onViewAttachedToWindow(view: View) {
                view.removeOnAttachStateChangeListener(this)
                view.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(view: View) = Unit
        })
    }
}

enum class DimensionSnapshotType {
    MARGINS,
    PADDING
}

data class DimensionSnapshot(
    val start: Int,
    val top: Int,
    val end: Int,
    val bottom: Int
)

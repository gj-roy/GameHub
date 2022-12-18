@file:JvmName("TypedArrayUtils")

package ca.on.hojat.gamenews.core.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import androidx.annotation.StyleableRes
import androidx.core.content.res.ResourcesCompat
import ca.on.hojat.gamenews.core.SdkInfo


fun TypedArray.getString(@StyleableRes index: Int, default: CharSequence = ""): CharSequence {
    return (getString(index) ?: default)
}

@SuppressLint("NewApi")
fun TypedArray.getFont(
    context: Context,
    @StyleableRes index: Int,
    default: Typeface
): Typeface {
    return if (SdkInfo.IS_AT_LEAST_OREO) {
        (getFont(index) ?: default)
    } else {
        getResourceId(index, -1)
            .takeIf { it != -1 }
            ?.let { ResourcesCompat.getFont(context, it) }
            ?: default
    }
}

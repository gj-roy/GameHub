@file:JvmName("EditTextUtils")

package ca.on.hojat.gamenews.shared.commons.ktx.views

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import ca.on.hojat.gamenews.shared.commons.core.SdkInfo

val EditText.isEmpty: Boolean
    get() = content.isEmpty()

val EditText.content: String
    get() = text.toString()

var EditText.isEditingEnabled: Boolean
    set(value) {
        isFocusable = value
    }
    get() = isFocusable

/**
 * Sets a cursor drawable of the EditText.
 *
 * Note: This solution is based on reflection since currently there is
 * not an option to set the drawable programmatically.
 * Based on the answer: https://stackoverflow.com/a/26543290
 *
 * Note: This solution won't work on PIE devices.
 *
 * @param drawable The drawable to set
 */
@SuppressLint("SoonBlockedPrivateApi", "DiscouragedPrivateApi")
fun EditText.setCursorDrawable(drawable: Drawable) {
    if (SdkInfo.IS_AT_LEAST_PIE) {
        return
    }

    try {
        val cursorDrawableResourceField =
            TextView::class.java.getDeclaredField("mCursorDrawableRes")
        cursorDrawableResourceField.isAccessible = true

        val editorField = TextView::class.java.getDeclaredField("mEditor")
        editorField.isAccessible = true

        val cursorDrawableFieldOwner = editorField.get(this)
        val cursorDrawableFieldClass = cursorDrawableFieldOwner.javaClass

        val cursorDrawableField = cursorDrawableFieldClass.getDeclaredField("mCursorDrawable")
        cursorDrawableField.isAccessible = true
        cursorDrawableField.set(
            cursorDrawableFieldOwner,
            arrayOf(drawable, drawable)
        )
    } catch (ignore: Exception) {
        // Ignore
    }
}

inline fun EditText.onTextChanged(crossinline callback: (String) -> Unit): TextWatcher {
    return doOnTextChanged { text, _, _, _ ->
        callback(text.toString())
    }
}

@file:JvmName("EditTextUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.widget.EditText

val EditText.isEmpty: Boolean
    get() = content.isEmpty()

val EditText.content: String
    get() = text.toString()
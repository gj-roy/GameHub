@file:JvmName("EditTextUtils")

package ca.on.hojat.gamenews.shared.commons.listeners.utils

import android.text.TextWatcher
import android.widget.EditText
import ca.on.hojat.gamenews.shared.commons.listeners.QueryListener

inline fun EditText.addQueryListener(
    crossinline onQueryEntered: (String) -> Unit = {},
    crossinline onQueryRemoved: () -> Unit = {}
): TextWatcher {
    val callback = object : QueryListener.Callback {

        override fun onQueryEntered(query: String) = onQueryEntered(query)
        override fun onQueryRemoved() = onQueryRemoved()
    }

    return QueryListener(callback)
}

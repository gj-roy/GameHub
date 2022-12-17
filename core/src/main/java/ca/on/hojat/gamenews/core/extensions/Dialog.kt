@file:JvmName("DialogUtils")

package ca.on.hojat.gamenews.core.extensions

import android.app.Dialog
import android.widget.Toast

fun Dialog.showLongToast(message: CharSequence): Toast {
    return context.showLongToast(message)
}

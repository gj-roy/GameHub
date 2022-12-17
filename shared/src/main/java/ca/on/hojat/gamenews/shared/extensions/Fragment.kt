@file:JvmName("FragmentUtils")
@file:Suppress("TooManyFunctions")

package ca.on.hojat.gamenews.shared.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import ca.on.hojat.gamenews.core.extensions.showLongToast

fun Fragment.showLongToast(message: CharSequence): Toast {
    return requireContext().showLongToast(message)
}


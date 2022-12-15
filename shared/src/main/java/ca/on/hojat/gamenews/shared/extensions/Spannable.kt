@file:JvmName("SpannableUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.text.Spannable

operator fun Spannable.set(
    start: Int,
    end: Int,
    span: Any
) {
    setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
}

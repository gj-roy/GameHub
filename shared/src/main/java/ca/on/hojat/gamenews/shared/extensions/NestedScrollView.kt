@file:JvmName("NestedScrollViewUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.view.View
import androidx.core.widget.NestedScrollView

fun NestedScrollView.scrollToTop() {
    postAction { fullScroll(View.FOCUS_UP) }
}

fun NestedScrollView.scrollToBottom() {
    postAction { fullScroll(View.FOCUS_DOWN) }
}

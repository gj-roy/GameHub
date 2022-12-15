@file:JvmName("NestedScrollViewUtils")

package ca.on.hojat.gamenews.shared.commons.ktx.views

import android.view.View
import androidx.core.widget.NestedScrollView
import ca.on.hojat.gamenews.shared.commons.ktx.postAction

fun NestedScrollView.scrollToTop() {
    postAction { fullScroll(View.FOCUS_UP) }
}

fun NestedScrollView.scrollToBottom() {
    postAction { fullScroll(View.FOCUS_DOWN) }
}

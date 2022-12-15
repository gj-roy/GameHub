package ca.on.hojat.gamenews.shared.commons.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class AdvancedViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {

    var isSwipingEnabled = true

    fun enableSwiping() {
        isSwipingEnabled = true
    }

    fun disableSwiping() {
        isSwipingEnabled = false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return (isSwipingEnabled && super.onTouchEvent(event))
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return (isSwipingEnabled && super.onInterceptTouchEvent(event))
    }
}

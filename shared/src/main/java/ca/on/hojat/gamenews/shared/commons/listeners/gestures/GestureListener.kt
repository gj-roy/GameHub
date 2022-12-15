package ca.on.hojat.gamenews.shared.commons.listeners.gestures

import android.view.GestureDetector
import android.view.MotionEvent

/**
 * A listener that provides hooks to some of the most
 * common gestures.
 */
class GestureListener(private val callback: Callback) : GestureDetector.SimpleOnGestureListener() {

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        callback.onSingleTap(event)

        return super.onSingleTapConfirmed(event)
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        callback.onDoubleTap(event)

        return super.onDoubleTap(event)
    }

    override fun onFling(
        startEvent: MotionEvent,
        endEvent: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        callback.onFling(startEvent, endEvent)

        val deltaX = (startEvent.x - endEvent.x)
        val deltaY = (startEvent.y - endEvent.y)

        detectHorizontalSwipes(deltaX, startEvent, endEvent)
        detectVerticalSwipes(deltaY, startEvent, endEvent)

        return super.onFling(startEvent, endEvent, velocityX, velocityY)
    }

    private fun detectHorizontalSwipes(
        deltaX: Float,
        startEvent: MotionEvent,
        endEvent: MotionEvent
    ) {
        if (deltaX > 0f) {
            callback.onSwipedToLeft(startEvent, endEvent)
        } else {
            callback.onSwipedToRight(startEvent, endEvent)
        }
    }

    private fun detectVerticalSwipes(
        deltaY: Float,
        startEvent: MotionEvent,
        endEvent: MotionEvent
    ) {
        if (deltaY > 0f) {
            callback.onSwipedToTop(startEvent, endEvent)
        } else {
            callback.onSwipedToBottom(startEvent, endEvent)
        }
    }

    interface Callback {

        fun onSingleTap(motionEvent: MotionEvent)

        fun onDoubleTap(motionEvent: MotionEvent)

        fun onFling(startEvent: MotionEvent, endEvent: MotionEvent)

        fun onSwipedToLeft(startEvent: MotionEvent, endEvent: MotionEvent)

        fun onSwipedToRight(startEvent: MotionEvent, endEvent: MotionEvent)

        fun onSwipedToTop(startEvent: MotionEvent, endEvent: MotionEvent)

        fun onSwipedToBottom(startEvent: MotionEvent, endEvent: MotionEvent)
    }
}

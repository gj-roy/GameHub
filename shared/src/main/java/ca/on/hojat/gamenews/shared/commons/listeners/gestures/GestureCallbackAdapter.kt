package ca.on.hojat.gamenews.shared.commons.listeners.gestures

import android.view.MotionEvent
import ca.on.hojat.gamenews.shared.commons.listeners.gestures.GestureListener

class GestureCallbackAdapter : GestureListener.Callback {

    override fun onSingleTap(motionEvent: MotionEvent) {}

    override fun onDoubleTap(motionEvent: MotionEvent) {}

    override fun onFling(startEvent: MotionEvent, endEvent: MotionEvent) {}

    override fun onSwipedToLeft(startEvent: MotionEvent, endEvent: MotionEvent) {}

    override fun onSwipedToRight(startEvent: MotionEvent, endEvent: MotionEvent) {}

    override fun onSwipedToTop(startEvent: MotionEvent, endEvent: MotionEvent) {}

    override fun onSwipedToBottom(startEvent: MotionEvent, endEvent: MotionEvent) {}
}

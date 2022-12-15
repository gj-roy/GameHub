package ca.on.hojat.gamenews.shared.commons.widgets

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import ca.on.hojat.gamenews.shared.R

class AdvancedEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {

    private val listeners = mutableListOf<TextWatcher>()

    fun setInputType(type: Int, shouldNotifyListeners: Boolean = true) {
        performAction(shouldNotifyListeners) {
            super.setInputType(type)
        }
    }

    fun setText(text: CharSequence, shouldNotifyListeners: Boolean = true) {
        performAction(shouldNotifyListeners) {
            super.setText(text)
        }
    }

    override fun addTextChangedListener(listener: TextWatcher) {
        listeners.add(listener)

        super.addTextChangedListener(listener)
    }

    override fun removeTextChangedListener(listener: TextWatcher) {
        if (listeners.isEmpty()) return

        listeners.indexOf(listener)
            .takeIf { it >= 0 }
            ?.let { listeners.removeAt(it) }

        super.removeTextChangedListener(listener)
    }

    fun clearTextChangedListeners() {
        if (listeners.isEmpty()) return

        for (listener in listeners) {
            super.removeTextChangedListener(listener)
        }

        listeners.clear()
    }

    private fun performAction(
        shouldNotifyListeners: Boolean,
        action: (() -> Unit)
    ) {
        if (shouldNotifyListeners || listeners.isEmpty()) {
            action()
            return
        }

        val listenersCopy = listeners.toMutableList()

        clearTextChangedListeners()
        action()

        for (listener in listenersCopy) {
            addTextChangedListener(listener)
        }
    }
}

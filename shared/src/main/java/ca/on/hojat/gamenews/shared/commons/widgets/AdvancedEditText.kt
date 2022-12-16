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

}

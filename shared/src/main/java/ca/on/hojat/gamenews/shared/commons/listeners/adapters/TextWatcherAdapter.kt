package ca.on.hojat.gamenews.shared.commons.listeners.adapters

import android.text.Editable
import android.text.TextWatcher

interface TextWatcherAdapter : TextWatcher {
    override fun beforeTextChanged(text: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(editable: Editable) {}
}

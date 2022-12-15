@file:JvmName("StringUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.graphics.Color
import java.util.Locale

val String.isColor: Boolean
    get() = try {
        Color.parseColor(this)
        true
    } catch (ignore: Throwable) {
        false
    }

/**
 * Truncates the string to the specified limit with the option
 * to ellipsize the ending of the string.
 *
 * @param characterLimit The number of characters to truncate
 * @param ellipsize Whether to ellipsize the ending or not
 *
 * @return The truncated string
 */
fun String.truncate(characterLimit: Int, ellipsize: Boolean = true): String {
    if (isBlank() || (length <= characterLimit)) {
        return this
    }

    return (substring(0, characterLimit) + (if (ellipsize) "…" else ""))
}

/**
 * Capitalizes the first letter of the string.
 *
 * @param locale The current locale
 *
 * @return The capitalized string
 */
fun String.capitalize(locale: Locale): String {
    if (isBlank()) {
        return this
    }

    return substring(0, 1).uppercase(locale) + substring(1)
}

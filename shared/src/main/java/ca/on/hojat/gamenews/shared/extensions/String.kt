@file:JvmName("StringUtils")

package ca.on.hojat.gamenews.shared.extensions

private const val SEPARATOR = ","

fun String.fromCsv(): List<String> {
    return split(SEPARATOR)
}

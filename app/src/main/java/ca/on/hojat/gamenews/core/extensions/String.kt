@file:JvmName("StringUtils")

package ca.on.hojat.gamenews.core.extensions

private const val SEPARATOR = ","

fun String.fromCsv(): List<String> {
    return split(SEPARATOR)
}

package ca.on.hojat.gamenews.shared.core.utils

private const val SEPARATOR = ","

fun Iterable<String>.toCsv(): String {
    return joinToString(separator = SEPARATOR)
}

fun String.fromCsv(): List<String> {
    return split(SEPARATOR)
}

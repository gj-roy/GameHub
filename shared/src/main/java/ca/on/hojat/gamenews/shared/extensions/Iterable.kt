package ca.on.hojat.gamenews.shared.extensions

private const val SEPARATOR = ","

fun Iterable<String>.toCsv(): String {
    return joinToString(separator = SEPARATOR)
}



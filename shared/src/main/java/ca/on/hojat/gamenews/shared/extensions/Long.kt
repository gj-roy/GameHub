package ca.on.hojat.gamenews.shared.extensions


val Long.isEven: Boolean
    get() = ((this and 1L) == 0L)

val Long.isOdd: Boolean
    get() = !isEven
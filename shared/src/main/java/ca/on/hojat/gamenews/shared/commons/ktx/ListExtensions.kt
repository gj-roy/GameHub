@file:JvmName("ListUtils")

package ca.on.hojat.gamenews.shared.commons.ktx

inline fun <T> List<T>.indexOfFirstOrNull(predicate: (T) -> Boolean): Int? {
    for ((index, item) in withIndex()) {
        if (predicate(item)) return index
    }

    return null
}

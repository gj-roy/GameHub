@file:JvmName("LiveDataUtils")

package ca.on.hojat.gamenews.shared.extensions

import androidx.lifecycle.LiveData

val <T> LiveData<T>.nonNullValue: T
    get() = checkNotNull(value)

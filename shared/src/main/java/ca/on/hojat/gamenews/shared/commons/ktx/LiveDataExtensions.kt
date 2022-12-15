@file:JvmName("LiveDataUtils")

package ca.on.hojat.gamenews.shared.commons.ktx

import androidx.lifecycle.LiveData

val <T> LiveData<T>.nonNullValue: T
    get() = checkNotNull(value)

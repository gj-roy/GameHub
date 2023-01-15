package ca.on.hojat.gamenews.core.data.database.common

import androidx.room.RoomDatabase

fun <T : RoomDatabase> RoomDatabase.Builder<T>.addTypeConverters(
    typeConverters: Set<RoomTypeConverter>
): RoomDatabase.Builder<T> = apply {
    typeConverters.forEach(::addTypeConverter)
}

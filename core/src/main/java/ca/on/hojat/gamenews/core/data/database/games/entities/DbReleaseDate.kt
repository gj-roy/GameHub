package ca.on.hojat.gamenews.core.data.database.games.entities

import kotlinx.serialization.Serializable

@Serializable
data class DbReleaseDate(
    val date: Long?,
    val year: Int?,
    val category: DbReleaseDateCategory,
)

package ca.on.hojat.gamenews.core.data.database.games.entities

import kotlinx.serialization.Serializable

@Serializable
data class DbPlatform(
    val abbreviation: String?,
    val name: String,
)

package ca.on.hojat.gamenews.shared.database.games.entities

import kotlinx.serialization.Serializable

@Serializable
data class DbPlatform(
    val abbreviation: String?,
    val name: String,
)

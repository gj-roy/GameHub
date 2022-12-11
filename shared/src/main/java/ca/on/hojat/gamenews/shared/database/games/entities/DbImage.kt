package ca.on.hojat.gamenews.shared.database.games.entities

import kotlinx.serialization.Serializable

@Serializable
data class DbImage(
    val id: String,
    val width: Int?,
    val height: Int?
)

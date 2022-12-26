package ca.on.hojat.gamenews.core.data.database.games.entities

import kotlinx.serialization.Serializable

@Serializable
data class DbVideo(
    val id: String,
    val name: String?,
)

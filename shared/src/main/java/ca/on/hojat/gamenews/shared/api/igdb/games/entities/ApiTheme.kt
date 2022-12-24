package ca.on.hojat.gamenews.shared.api.igdb.games.entities

import ca.on.hojat.gamenews.core.data.api.igdbcalypse.serialization.annotations.Apicalypse
import ca.on.hojat.gamenews.core.data.api.igdbcalypse.serialization.annotations.ApicalypseClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Some examples are Action, Fantasy, Historical, Open World, and so on...
 */
@ApicalypseClass
@Serializable
data class ApiTheme(
    @Apicalypse(Schema.NAME)
    @SerialName(Schema.NAME)
    val name: String,
) {

    object Schema {
        const val NAME = "name"
    }
}

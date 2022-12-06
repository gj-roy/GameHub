package ca.on.hojat.gamenews.api.igdb.games.entities

import ca.on.hojat.gamenews.api.igdbcalypse.serialization.annotations.Apicalypse
import ca.on.hojat.gamenews.api.igdbcalypse.serialization.annotations.ApicalypseClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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

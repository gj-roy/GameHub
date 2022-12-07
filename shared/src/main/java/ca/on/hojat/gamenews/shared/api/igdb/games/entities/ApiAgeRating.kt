package ca.on.hojat.gamenews.shared.api.igdb.games.entities

import ca.on.hojat.gamenews.shared.api.igdbcalypse.serialization.annotations.Apicalypse
import ca.on.hojat.gamenews.shared.api.igdbcalypse.serialization.annotations.ApicalypseClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ApicalypseClass
@Serializable
data class ApiAgeRating(
    @Apicalypse(Schema.CATEGORY)
    @SerialName(Schema.CATEGORY)
    val category: ApiAgeRatingCategory,
    @Apicalypse(Schema.RATING)
    @SerialName(Schema.RATING)
    val type: ApiAgeRatingType,
) {

    object Schema {
        const val CATEGORY = "category"
        const val RATING = "rating"
    }
}

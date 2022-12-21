package ca.on.hojat.gamenews.core.formatters

import ca.on.hojat.gamenews.core.R
import ca.on.hojat.gamenews.core.domain.entities.AgeRatingCategory
import ca.on.hojat.gamenews.core.domain.entities.AgeRatingType
import ca.on.hojat.gamenews.core.domain.entities.Game
import ca.on.hojat.gamenews.core.providers.StringProvider
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

interface GameAgeRatingFormatter {
    fun formatAgeRating(game: Game): String
}

@BindType
internal class GameAgeRatingFormatterImpl @Inject constructor(
    private val stringProvider: StringProvider
) : GameAgeRatingFormatter {

    override fun formatAgeRating(game: Game): String {
        val ageRatings = game.ageRatings.filterNot {
            it.category == AgeRatingCategory.UNKNOWN ||
                    it.type == AgeRatingType.UNKNOWN
        }

        val ageRating = ageRatings.firstOrNull { it.category == AgeRatingCategory.PEGI }
            ?: ageRatings.firstOrNull { it.category == AgeRatingCategory.ESRB }
            ?: return stringProvider.getString(R.string.not_available_abbr)

        return stringProvider.getString(
            R.string.age_rating_template,
            ageRating.category.title,
            ageRating.type.title
        )
    }
}

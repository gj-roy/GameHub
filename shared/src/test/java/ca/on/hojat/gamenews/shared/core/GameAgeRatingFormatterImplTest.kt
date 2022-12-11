package ca.on.hojat.gamenews.shared.core

import ca.on.hojat.gamenews.shared.core.formatters.GameAgeRatingFormatterImpl
import ca.on.hojat.gamenews.shared.core.providers.StringProvider
import com.google.common.truth.Truth.assertThat
import ca.on.hojat.gamenews.shared.domain.games.entities.AgeRating
import ca.on.hojat.gamenews.shared.domain.games.entities.AgeRatingCategory
import ca.on.hojat.gamenews.shared.domain.games.entities.AgeRatingType
import ca.on.hojat.gamenews.shared.testing.domain.DOMAIN_GAME
import org.junit.Before
import org.junit.Test

internal class GameAgeRatingFormatterImplTest {

    private lateinit var stringProvider: FakeStringProvider
    private lateinit var sut: GameAgeRatingFormatterImpl

    @Before
    fun setup() {
        stringProvider = FakeStringProvider()
        sut = GameAgeRatingFormatterImpl(stringProvider)
    }

    @Test
    fun `Returns properly formatted string with age rating`() {
        val game = DOMAIN_GAME.copy(
            ageRatings = listOf(
                AgeRating(AgeRatingCategory.PEGI, AgeRatingType.AO)
            )
        )

        sut.formatAgeRating(game)

        assertThat(stringProvider.isRatingAvailable).isTrue()
    }

    @Test
    fun `Returns not available string when game does not contain any ratings`() {
        sut.formatAgeRating(DOMAIN_GAME)

        assertThat(stringProvider.isRatingNotAvailable).isTrue()
    }

    @Test
    fun `Returns not available string when game does not contain valid ratings`() {
        val game = DOMAIN_GAME.copy(
            ageRatings = listOf(
                AgeRating(AgeRatingCategory.UNKNOWN, AgeRatingType.AO),
                AgeRating(AgeRatingCategory.PEGI, AgeRatingType.UNKNOWN)
            )
        )

        sut.formatAgeRating(game)

        assertThat(stringProvider.isRatingNotAvailable).isTrue()
    }

    private class FakeStringProvider : StringProvider {

        var isRatingAvailable = false
        var isRatingNotAvailable = false

        override fun getString(id: Int, vararg args: Any): String {
            isRatingAvailable = (id == R.string.age_rating_template)
            isRatingNotAvailable = (id == R.string.not_available_abbr)

            return ""
        }

        override fun getQuantityString(id: Int, quantity: Int, vararg formatArgs: Any): String {
            return ""
        }
    }
}

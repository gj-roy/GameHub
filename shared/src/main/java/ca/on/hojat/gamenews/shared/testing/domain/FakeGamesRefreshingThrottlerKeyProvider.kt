package ca.on.hojat.gamenews.shared.testing.domain

import ca.on.hojat.gamenews.shared.domain.common.entities.Pagination
import ca.on.hojat.gamenews.shared.domain.games.common.throttling.GamesRefreshingThrottlerKeyProvider
import ca.on.hojat.gamenews.core.domain.entities.Company
import ca.on.hojat.gamenews.core.domain.entities.Game

class FakeGamesRefreshingThrottlerKeyProvider : GamesRefreshingThrottlerKeyProvider {

    override fun providePopularGamesKey(pagination: Pagination): String {
        return "providePopularGamesKey"
    }

    override fun provideRecentlyReleasedGamesKey(pagination: Pagination): String {
        return "provideRecentlyReleasedGamesKey"
    }

    override fun provideComingSoonGamesKey(pagination: Pagination): String {
        return "provideComingSoonGamesKey"
    }

    override fun provideMostAnticipatedGamesKey(pagination: Pagination): String {
        return "provideMostAnticipatedGamesKey"
    }

    override fun provideCompanyDevelopedGamesKey(
        company: Company,
        pagination: Pagination
    ): String {
        return "provideCompanyDevelopedGamesKey"
    }

    override fun provideSimilarGamesKey(game: Game, pagination: Pagination): String {
        return "provideSimilarGamesKey"
    }
}

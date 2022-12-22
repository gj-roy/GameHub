package ca.on.hojat.gamenews.core.domain.games.common.throttling

interface GamesRefreshingThrottler {
    suspend fun canRefreshGames(key: String): Boolean
    suspend fun updateGamesLastRefreshTime(key: String)
    suspend fun canRefreshCompanyDevelopedGames(key: String): Boolean
    suspend fun canRefreshSimilarGames(key: String): Boolean
}

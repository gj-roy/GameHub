package ca.on.hojat.gamenews.shared.domain.games.datastores

import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.shared.domain.common.entities.Pagination
import ca.on.hojat.gamenews.core.domain.entities.Company
import ca.on.hojat.gamenews.core.domain.entities.Game

interface GamesRemoteDataStore {
    suspend fun searchGames(searchQuery: String, pagination: Pagination): DomainResult<List<Game>>
    suspend fun getPopularGames(pagination: Pagination): DomainResult<List<Game>>
    suspend fun getRecentlyReleasedGames(pagination: Pagination): DomainResult<List<Game>>
    suspend fun getComingSoonGames(pagination: Pagination): DomainResult<List<Game>>
    suspend fun getMostAnticipatedGames(pagination: Pagination): DomainResult<List<Game>>
    suspend fun getCompanyDevelopedGames(
        company: Company,
        pagination: Pagination
    ): DomainResult<List<Game>>

    suspend fun getSimilarGames(game: Game, pagination: Pagination): DomainResult<List<Game>>
}

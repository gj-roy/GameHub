package ca.on.hojat.gamehub.core.domain.games.repository

import ca.on.hojat.gamehub.core.domain.DomainResult
import ca.on.hojat.gamehub.core.domain.entities.Pagination
import ca.on.hojat.gamehub.core.domain.entities.Company
import ca.on.hojat.gamehub.core.domain.entities.Game

interface GamesRemoteDataSource {
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

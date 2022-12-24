package ca.on.hojat.gamenews.shared.data.games.datastores.igdb

import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.core.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.entities.Company
import ca.on.hojat.gamenews.core.domain.entities.Game
import ca.on.hojat.gamenews.core.domain.entities.Pagination
import ca.on.hojat.gamenews.core.domain.games.datastores.GamesRemoteDataStore
import ca.on.hojat.gamenews.core.data.api.ApiErrorMapper
import ca.on.hojat.gamenews.core.data.api.common.ApiResult
import ca.on.hojat.gamenews.core.data.api.igdb.games.entities.ApiGame
import ca.on.hojat.gamenews.shared.api.igdb.games.GamesEndpoint
import ca.on.hojat.gamenews.shared.api.igdb.games.requests.GetComingSoonGamesRequest
import ca.on.hojat.gamenews.shared.api.igdb.games.requests.GetGamesRequest
import ca.on.hojat.gamenews.shared.api.igdb.games.requests.GetMostAnticipatedGamesRequest
import ca.on.hojat.gamenews.shared.api.igdb.games.requests.GetPopularGamesRequest
import ca.on.hojat.gamenews.shared.api.igdb.games.requests.GetRecentlyReleasedGamesRequest
import ca.on.hojat.gamenews.shared.api.igdb.games.requests.SearchGamesRequest
import ca.on.hojat.gamenews.shared.data.games.common.DiscoveryGamesReleaseDatesProvider
import com.github.michaelbull.result.mapEither
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@BindType
internal class GamesIgdbDataStore @Inject constructor(
    private val gamesEndpoint: GamesEndpoint,
    private val releaseDatesProvider: DiscoveryGamesReleaseDatesProvider,
    private val dispatcherProvider: DispatcherProvider,
    private val igdbGameMapper: IgdbGameMapper,
    private val apiErrorMapper: ApiErrorMapper,
) : GamesRemoteDataStore {

    override suspend fun searchGames(
        searchQuery: String,
        pagination: Pagination
    ): DomainResult<List<Game>> {
        return gamesEndpoint
            .searchGames(
                SearchGamesRequest(
                    searchQuery = searchQuery,
                    offset = pagination.offset,
                    limit = pagination.limit,
                )
            )
            .toDataStoreResult()
    }

    override suspend fun getPopularGames(pagination: Pagination): DomainResult<List<Game>> {
        return gamesEndpoint
            .getPopularGames(
                GetPopularGamesRequest(
                    minReleaseDateTimestamp = releaseDatesProvider.getPopularGamesMinReleaseDate(),
                    offset = pagination.offset,
                    limit = pagination.limit,
                )
            )
            .toDataStoreResult()
    }

    override suspend fun getRecentlyReleasedGames(pagination: Pagination): DomainResult<List<Game>> {
        return gamesEndpoint
            .getRecentlyReleasedGames(
                GetRecentlyReleasedGamesRequest(
                    minReleaseDateTimestamp = releaseDatesProvider.getRecentlyReleasedGamesMinReleaseDate(),
                    maxReleaseDateTimestamp = releaseDatesProvider.getRecentlyReleasedGamesMaxReleaseDate(),
                    offset = pagination.offset,
                    limit = pagination.limit,
                )
            )
            .toDataStoreResult()
    }

    override suspend fun getComingSoonGames(pagination: Pagination): DomainResult<List<Game>> {
        return gamesEndpoint
            .getComingSoonGames(
                GetComingSoonGamesRequest(
                    minReleaseDateTimestamp = releaseDatesProvider.getComingSoonGamesMinReleaseDate(),
                    offset = pagination.offset,
                    limit = pagination.limit,
                )
            )
            .toDataStoreResult()
    }

    override suspend fun getMostAnticipatedGames(pagination: Pagination): DomainResult<List<Game>> {
        return gamesEndpoint
            .getMostAnticipatedGames(
                GetMostAnticipatedGamesRequest(
                    minReleaseDateTimestamp = releaseDatesProvider.getMostAnticipatedGamesMinReleaseDate(),
                    offset = pagination.offset,
                    limit = pagination.limit,
                )
            )
            .toDataStoreResult()
    }

    override suspend fun getCompanyDevelopedGames(
        company: Company,
        pagination: Pagination
    ): DomainResult<List<Game>> {
        return gamesEndpoint
            .getGames(
                GetGamesRequest(
                    gameIds = company.developedGames,
                    offset = pagination.offset,
                    limit = pagination.limit,
                )
            )
            .toDataStoreResult()
    }

    override suspend fun getSimilarGames(
        game: Game,
        pagination: Pagination
    ): DomainResult<List<Game>> {
        return gamesEndpoint
            .getGames(
                GetGamesRequest(
                    gameIds = game.similarGames,
                    offset = pagination.offset,
                    limit = pagination.limit,
                )
            )
            .toDataStoreResult()
    }

    private suspend fun ApiResult<List<ApiGame>>.toDataStoreResult(): DomainResult<List<Game>> {
        return withContext(dispatcherProvider.computation) {
            mapEither(igdbGameMapper::mapToDomainGames, apiErrorMapper::mapToDomainError)
        }
    }
}

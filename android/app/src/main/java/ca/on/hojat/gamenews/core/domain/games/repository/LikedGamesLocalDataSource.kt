package ca.on.hojat.gamenews.core.domain.games.repository

import ca.on.hojat.gamenews.core.domain.entities.Pagination
import ca.on.hojat.gamenews.core.domain.entities.Game
import kotlinx.coroutines.flow.Flow

interface LikedGamesLocalDataSource {
    suspend fun likeGame(gameId: Int)
    suspend fun unlikeGame(gameId: Int)
    suspend fun isGameLiked(gameId: Int): Boolean

    fun observeGameLikeState(gameId: Int): Flow<Boolean>
    fun observeLikedGames(pagination: Pagination): Flow<List<Game>>
}

package ca.on.hojat.gamenews.shared.data.games.database

import app.cash.turbine.test
import ca.on.hojat.gamenews.shared.data.games.datastores.database.DbGameMapper
import ca.on.hojat.gamenews.shared.data.games.datastores.database.LikedGameFactory
import ca.on.hojat.gamenews.shared.data.games.datastores.database.LikedGamesDatabaseDataStore
import ca.on.hojat.gamenews.shared.data.games.datastores.database.mapToDatabaseGames
import ca.on.hojat.gamenews.shared.database.games.entities.DbGame
import ca.on.hojat.gamenews.shared.database.games.entities.DbLikedGame
import ca.on.hojat.gamenews.shared.database.games.tables.LikedGamesTable
import ca.on.hojat.gamenews.shared.testing.domain.DOMAIN_GAMES
import ca.on.hojat.gamenews.shared.testing.domain.MainCoroutineRule
import ca.on.hojat.gamenews.shared.testing.domain.PAGINATION
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val GAME_ID = 100
private const val ANOTHER_GAME_ID = 110

internal class LikedGamesDatabaseDataStoreTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var likedGamesTable: FakeLikedGamesTable
    private lateinit var dbGameMapper: DbGameMapper
    private lateinit var sut: LikedGamesDatabaseDataStore

    @Before
    fun setup() {
        likedGamesTable = FakeLikedGamesTable()
        dbGameMapper = DbGameMapper()
        sut = LikedGamesDatabaseDataStore(
            likedGamesTable = likedGamesTable,
            likedGameFactory = FakeLikedGameFactory(),
            dispatcherProvider = mainCoroutineRule.dispatcherProvider,
            dbGameMapper = dbGameMapper
        )
    }

    @Test
    fun `Likes game successfully`() {
        runTest {
            sut.likeGame(GAME_ID)

            assertThat(sut.isGameLiked(GAME_ID)).isTrue()
        }
    }

    @Test
    fun `Unlikes game successfully`() {
        runTest {
            sut.likeGame(GAME_ID)
            sut.unlikeGame(GAME_ID)

            assertThat(sut.isGameLiked(GAME_ID)).isFalse()
        }
    }

    @Test
    fun `Validates that unliked game is unliked`() {
        runTest {
            assertThat(sut.isGameLiked(gameId = ANOTHER_GAME_ID)).isFalse()
        }
    }

    @Test
    fun `Observes game like state successfully`() {
        runTest {
            sut.likeGame(GAME_ID)

            sut.observeGameLikeState(GAME_ID).test {
                assertThat(awaitItem()).isTrue()
                awaitComplete()
            }

            sut.observeGameLikeState(ANOTHER_GAME_ID).test {
                assertThat(awaitItem()).isFalse()
                awaitComplete()
            }
        }
    }

    @Test
    fun `Observes liked games successfully`() {
        runTest {
            val dbGames = dbGameMapper.mapToDatabaseGames(DOMAIN_GAMES)

            likedGamesTable.dbGamesToObserve = dbGames

            sut.observeLikedGames(PAGINATION).test {
                assertThat(awaitItem()).isEqualTo(DOMAIN_GAMES)
                awaitComplete()
            }
        }
    }

    private class FakeLikedGamesTable : LikedGamesTable {

        var likedGamesMap = mutableMapOf<Int, DbLikedGame>()
        var dbGamesToObserve = listOf<DbGame>()

        override suspend fun saveLikedGame(likedGame: DbLikedGame) {
            likedGamesMap[likedGame.gameId] = likedGame
        }

        override suspend fun deleteLikedGame(gameId: Int) {
            likedGamesMap.remove(gameId)
        }

        override suspend fun isGameLiked(gameId: Int): Boolean {
            return likedGamesMap.containsKey(gameId)
        }

        override fun observeGameLikeState(gameId: Int): Flow<Boolean> {
            return flowOf(likedGamesMap.contains(gameId))
        }

        override fun observeLikedGames(offset: Int, limit: Int): Flow<List<DbGame>> {
            return flowOf(dbGamesToObserve)
        }
    }

    private class FakeLikedGameFactory : LikedGameFactory {

        override fun createLikedGame(gameId: Int): DbLikedGame {
            return DbLikedGame(
                id = 1,
                gameId = gameId,
                likeTimestamp = 500L
            )
        }
    }
}

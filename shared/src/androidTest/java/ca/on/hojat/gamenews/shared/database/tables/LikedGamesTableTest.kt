package ca.on.hojat.gamenews.shared.database.tables

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import ca.on.hojat.gamenews.shared.database.DB_GAMES
import ca.on.hojat.gamenews.shared.database.DB_LIKED_GAME
import ca.on.hojat.gamenews.shared.database.DB_LIKED_GAMES
import ca.on.hojat.gamenews.core.data.database.common.di.DatabaseModule
import ca.on.hojat.gamenews.core.data.database.games.tables.GamesTable
import ca.on.hojat.gamenews.core.data.database.games.tables.LikedGamesTable
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
internal class LikedGamesTableTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var gamesTable: GamesTable
    @Inject
    lateinit var sut: LikedGamesTable

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun likes_game_and_verifies_that_it_is_liked() {
        runTest {
            sut.saveLikedGame(DB_LIKED_GAME)

            assertThat(sut.isGameLiked(DB_LIKED_GAME.gameId)).isTrue()
        }
    }

    @Test
    fun likes_game_unlikes_it_and_verifies_that_it_is_unliked_by_checking() {
        runTest {
            sut.saveLikedGame(DB_LIKED_GAME)
            sut.deleteLikedGame(DB_LIKED_GAME.gameId)

            assertThat(sut.isGameLiked(DB_LIKED_GAME.gameId)).isFalse()
        }
    }

    @Test
    fun verifies_that_unliked_game_is_unliked() {
        runTest {
            assertThat(sut.isGameLiked(100)).isFalse()
        }
    }

    @Test
    fun likes_game_and_observes_that_it_is_liked() {
        runTest {
            sut.saveLikedGame(DB_LIKED_GAME)

            sut.observeGameLikeState(DB_LIKED_GAME.gameId).test {
                assertThat(awaitItem()).isTrue()
            }
        }
    }

    @Test
    fun likes_game_unlikes_it_and_verifies_that_it_is_unliked_by_observing() {
        runTest {
            sut.saveLikedGame(DB_LIKED_GAME)
            sut.deleteLikedGame(DB_LIKED_GAME.gameId)

            sut.observeGameLikeState(DB_LIKED_GAME.gameId).test {
                assertThat(awaitItem()).isFalse()
            }
        }
    }

    @Test
    fun likes_games_and_observes_liked_games() {
        runTest {
            DB_LIKED_GAMES.forEach { sut.saveLikedGame(it) }
            gamesTable.saveGames(DB_GAMES)

            val expectedGames = DB_GAMES
                .sortedByDescending { game ->
                    DB_LIKED_GAMES
                        .first { likedGame -> likedGame.gameId == game.id }
                        .likeTimestamp
                }

            sut.observeLikedGames(offset = 0, limit = expectedGames.size)
                .test {
                    assertThat(awaitItem()).isEqualTo(expectedGames)
                }
        }
    }
}

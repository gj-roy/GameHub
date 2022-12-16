package ca.on.hojat.gamenews.shared.database.tables

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import ca.on.hojat.gamenews.shared.database.DB_GAMES
import ca.on.hojat.gamenews.shared.database.games.entities.DbGame
import com.google.common.truth.Truth.assertThat
import ca.on.hojat.gamenews.shared.database.common.di.DatabaseModule
import ca.on.hojat.gamenews.shared.database.games.tables.GamesTable
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
internal class GamesTableTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var sut: GamesTable

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun saves_games_and_gets_game_by_ID() {
        runTest {
            sut.saveGames(DB_GAMES)

            val expectedGame = DB_GAMES.first()

            assertThat(sut.getGame(expectedGame.id)).isEqualTo(expectedGame)
        }
    }

    @Test
    fun saves_games_and_gets_null_for_non_existent_game_ID() {
        runTest {
            sut.saveGames(DB_GAMES)

            assertThat(sut.getGame(id = 500)).isNull()
        }
    }

    @Test
    fun saves_games_and_gets_games_by_IDs() {
        runTest {
            sut.saveGames(DB_GAMES)

            assertThat(
                sut.getGames(
                    ids = DB_GAMES.map(DbGame::id),
                    offset = 0,
                    limit = DB_GAMES.size
                )
            ).isEqualTo(DB_GAMES)
        }
    }

    @Test
    fun saves_games_and_gets_empty_game_list_for_non_existent_game_IDs() {
        runTest {
            sut.saveGames(DB_GAMES)

            assertThat(
                sut.getGames(
                    ids = listOf(100, 200, 300),
                    offset = 0,
                    limit = DB_GAMES.size
                )
            ).isEqualTo(emptyList<DbGame>())
        }
    }

    @Test
    fun saves_games_and_gets_some_games_for_some_game_IDs() {
        runTest {
            sut.saveGames(DB_GAMES)

            val expectedGames = listOf(DB_GAMES.first(), DB_GAMES.last())

            assertThat(
                sut.getGames(
                    ids = expectedGames.map(DbGame::id),
                    offset = 0,
                    limit = expectedGames.size
                )
            ).isEqualTo(expectedGames)
        }
    }

    @Test
    fun saves_games_and_gets_sorted_games_by_searching_with_upper_case_game_name() {
        runTest {
            sut.saveGames(DB_GAMES)

            val expectedGames = DB_GAMES
                .sortedByDescending(DbGame::totalRating)

            assertThat(
                sut.searchGames(
                    searchQuery = "Game",
                    offset = 0,
                    limit = DB_GAMES.size
                )
            ).isEqualTo(expectedGames)
        }
    }

    @Test
    fun saves_games_and_gets_sorted_games_by_searching_with_lower_case_game_name() {
        runTest {
            sut.saveGames(DB_GAMES)

            val expectedGames = DB_GAMES
                .sortedByDescending(DbGame::totalRating)

            assertThat(
                sut.searchGames(
                    searchQuery = "game",
                    offset = 0,
                    limit = DB_GAMES.size
                )
            ).isEqualTo(expectedGames)
        }
    }

    @Test
    fun saves_games_and_gets_empty_game_list_by_searching_with_not_available_game_name() {
        runTest {
            sut.saveGames(DB_GAMES)

            assertThat(
                sut.searchGames(
                    searchQuery = "shadow of the colossus",
                    offset = 0,
                    limit = DB_GAMES.size
                )
            ).isEqualTo(emptyList<DbGame>())
        }
    }

    @Test
    fun saves_games_and_gets_empty_game_list_by_searching_with_word_that_ends_with_target_game_name() {
        runTest {
            sut.saveGames(DB_GAMES)

            assertThat(
                sut.searchGames(
                    searchQuery = "endgame",
                    offset = 0,
                    limit = DB_GAMES.size
                )
            ).isEqualTo(emptyList<DbGame>())
        }
    }

    @Test
    fun saves_specific_games_and_gets_properly_sorted_games_by_searching_with_existing_game_name() {
        runTest {
            val gamesToSave = listOf(
                DB_GAMES[0].copy(totalRating = null),
                DB_GAMES[1].copy(totalRating = 20.0),
                DB_GAMES[2].copy(totalRating = 20.0),
                DB_GAMES[3],
                DB_GAMES[4],
            )

            sut.saveGames(gamesToSave)

            val expectedGames = gamesToSave.sortedWith(
                compareByDescending(DbGame::totalRating)
                    .thenBy(DbGame::id)
            )

            assertThat(
                sut.searchGames(
                    searchQuery = "Game",
                    offset = 0,
                    limit = DB_GAMES.size
                )
            ).isEqualTo(expectedGames)
        }
    }

    @Test
    fun saves_popular_games_and_observes_popular_games() {
        runTest {
            sut.saveGames(DB_GAMES)

            val expectedGames = DB_GAMES
                .sortedByDescending(DbGame::totalRating)

            sut.observePopularGames(
                minReleaseDateTimestamp = 50L,
                offset = 0,
                limit = DB_GAMES.size
            ).test {
                assertThat(awaitItem()).isEqualTo(expectedGames)
            }
        }
    }

    @Test
    fun saves_popular_games_and_observes_only_games_that_have_users_rating() {
        runTest {
            val gamesToSave = buildList {
                add(DB_GAMES[0].copy(usersRating = null))
                addAll(DB_GAMES.drop(1))
            }

            sut.saveGames(gamesToSave)

            val expectedGames = gamesToSave
                .filter { it.usersRating != null }
                .sortedByDescending(DbGame::totalRating)

            sut.observePopularGames(
                minReleaseDateTimestamp = 50L,
                offset = 0,
                limit = gamesToSave.size
            ).test {
                assertThat(awaitItem()).isEqualTo(expectedGames)
            }
        }
    }

    @Test
    fun saves_popular_games_and_observes_only_games_that_have_release_date() {
        runTest {
            val gamesToSave = buildList {
                add(DB_GAMES[0].copy(usersRating = null))
                add(DB_GAMES[1].copy(releaseDate = null))
                addAll(DB_GAMES.drop(2))
            }

            sut.saveGames(gamesToSave)

            val expectedGames = gamesToSave
                .filter { it.usersRating != null }
                .filter { it.releaseDate != null }
                .sortedByDescending(DbGame::totalRating)

            sut.observePopularGames(
                minReleaseDateTimestamp = 50L,
                offset = 0,
                limit = gamesToSave.size
            ).test {
                assertThat(awaitItem()).isEqualTo(expectedGames)
            }
        }
    }

    @Test
    fun saves_popular_games_and_observes_only_games_that_have_min_release_date() {
        runTest {
            val gamesToSave = buildList {
                add(DB_GAMES[0].copy(usersRating = null))
                add(DB_GAMES[1].copy(releaseDate = null))
                addAll(DB_GAMES.drop(2))
            }
            val minReleaseDateTimestamp = 300L

            sut.saveGames(gamesToSave)

            val expectedGames = gamesToSave
                .filter { it.usersRating != null }
                .filter { it.releaseDate != null }
                .filter { it.releaseDate != null && it.releaseDate!! > minReleaseDateTimestamp }
                .sortedByDescending(DbGame::totalRating)

            sut.observePopularGames(
                minReleaseDateTimestamp = minReleaseDateTimestamp,
                offset = 0,
                limit = gamesToSave.size
            ).test {
                assertThat(awaitItem()).isEqualTo(expectedGames)
            }
        }
    }

    @Test
    fun saves_recently_released_games_and_observes_recently_released_games() {
        runTest {
            sut.saveGames(DB_GAMES)

            val expectedGames = DB_GAMES
                .sortedByDescending(DbGame::releaseDate)

            sut.observeRecentlyReleasedGames(
                minReleaseDateTimestamp = 50L,
                maxReleaseDateTimestamp = 1000L,
                offset = 0,
                limit = DB_GAMES.size
            ).test {
                assertThat(awaitItem()).isEqualTo(expectedGames)
            }
        }
    }

    @Test
    fun saves_recently_released_games_and_observes_recently_released_games_that_have_release_date() {
        runTest {
            val gamesToSave = buildList {
                add(DB_GAMES[0].copy(releaseDate = null))
                addAll(DB_GAMES.drop(1))
            }

            sut.saveGames(gamesToSave)

            val expectedGames = gamesToSave
                .filter { it.releaseDate != null }
                .sortedByDescending(DbGame::releaseDate)

            sut.observeRecentlyReleasedGames(
                minReleaseDateTimestamp = 50L,
                maxReleaseDateTimestamp = 1000L,
                offset = 0,
                limit = gamesToSave.size
            ).test {
                assertThat(awaitItem()).isEqualTo(expectedGames)
            }
        }
    }

    @Test
    fun saves_recently_released_games_and_observes_recently_released_games_that_have_min_release_date() {
        runTest {
            val gamesToSave = buildList {
                add(DB_GAMES[0].copy(releaseDate = null))
                addAll(DB_GAMES.drop(1))
            }
            val minReleaseDateTimestamp = 300L

            sut.saveGames(gamesToSave)

            val expectedGames = gamesToSave
                .filter { it.releaseDate != null }
                .filter { it.releaseDate != null && it.releaseDate!! > minReleaseDateTimestamp }
                .sortedByDescending(DbGame::releaseDate)

            sut.observeRecentlyReleasedGames(
                minReleaseDateTimestamp = minReleaseDateTimestamp,
                maxReleaseDateTimestamp = 1000L,
                offset = 0,
                limit = gamesToSave.size
            ).test {
                assertThat(awaitItem()).isEqualTo(expectedGames)
            }
        }
    }

    @Test
    fun saves_recently_released_games_and_observes_recently_released_games_that_have_max_release_date() {
        runTest {
            val gamesToSave = buildList {
                add(DB_GAMES[0].copy(releaseDate = null))
                addAll(DB_GAMES.drop(1))
            }
            val minReleaseDateTimestamp = 200L
            val maxReleaseDateTimestamp = 400L

            sut.saveGames(gamesToSave)

            val expectedGames = gamesToSave
                .filter { it.releaseDate != null }
                .filter { it.releaseDate != null && it.releaseDate!! > minReleaseDateTimestamp }
                .filter { it.releaseDate != null && it.releaseDate!! < maxReleaseDateTimestamp }
                .sortedByDescending(DbGame::releaseDate)

            sut.observeRecentlyReleasedGames(
                minReleaseDateTimestamp = minReleaseDateTimestamp,
                maxReleaseDateTimestamp = maxReleaseDateTimestamp,
                offset = 0,
                limit = gamesToSave.size
            ).test {
                assertThat(awaitItem()).isEqualTo(expectedGames)
            }
        }
    }

    @Test
    fun saves_coming_soon_games_and_observes_coming_soon_games() {
        runTest {
            sut.saveGames(DB_GAMES)

            val expectedGames = DB_GAMES.sortedBy(DbGame::releaseDate)

            sut.observeComingSoonGames(
                minReleaseDateTimestamp = 50L,
                offset = 0,
                limit = DB_GAMES.size
            ).test {
                assertThat(awaitItem()).isEqualTo(expectedGames)
            }
        }
    }

    @Test
    fun saves_coming_soon_games_and_observes_coming_soon_games_that_have_release_date() {
        runTest {
            val gamesToSave = buildList {
                add(DB_GAMES[0].copy(releaseDate = null))
                addAll(DB_GAMES.drop(1))
            }

            sut.saveGames(gamesToSave)

            val expectedGames = gamesToSave
                .filter { it.releaseDate != null }
                .sortedBy(DbGame::releaseDate)

            sut.observeComingSoonGames(
                minReleaseDateTimestamp = 50L,
                offset = 0,
                limit = gamesToSave.size
            ).test {
                assertThat(awaitItem()).isEqualTo(expectedGames)
            }
        }
    }

    @Test
    fun saves_coming_soon_games_and_observes_coming_soon_games_that_have_min_release_date() {
        runTest {
            val gamesToSave = buildList {
                add(DB_GAMES[0].copy(releaseDate = null))
                addAll(DB_GAMES.drop(1))
            }
            val minReleaseDateTimestamp = 300L

            sut.saveGames(gamesToSave)

            val expectedGames = gamesToSave
                .filter { it.releaseDate != null }
                .filter { it.releaseDate != null && it.releaseDate!! > minReleaseDateTimestamp }
                .sortedBy(DbGame::releaseDate)

            sut.observeComingSoonGames(
                minReleaseDateTimestamp = minReleaseDateTimestamp,
                offset = 0,
                limit = gamesToSave.size
            ).test {
                assertThat(awaitItem()).isEqualTo(expectedGames)
            }
        }
    }

    @Test
    fun saves_most_anticipated_games_and_observes_most_anticipated_games() {
        runTest {
            sut.saveGames(DB_GAMES)

            val expectedGames = DB_GAMES
                .sortedByDescending(DbGame::hypeCount)

            sut.observeMostAnticipatedGames(
                minReleaseDateTimestamp = 50L,
                offset = 0,
                limit = DB_GAMES.size
            ).test {
                assertThat(awaitItem()).isEqualTo(expectedGames)
            }
        }
    }

    @Test
    fun saves_most_anticipated_games_and_observes_most_anticipated_games_that_have_release_date() {
        runTest {
            val gamesToSave = buildList {
                add(DB_GAMES[0].copy(releaseDate = null))
                addAll(DB_GAMES.drop(1))
            }

            sut.saveGames(gamesToSave)

            val expectedGames = gamesToSave
                .filter { it.releaseDate != null }
                .sortedByDescending(DbGame::hypeCount)

            sut.observeMostAnticipatedGames(
                minReleaseDateTimestamp = 50L,
                offset = 0,
                limit = gamesToSave.size
            ).test {
                assertThat(awaitItem()).isEqualTo(expectedGames)
            }
        }
    }

    @Test
    fun saves_most_anticipated_games_and_observes_most_anticipated_games_that_have_min_release_date() {
        runTest {
            val gamesToSave = buildList {
                add(DB_GAMES[0].copy(releaseDate = null))
                addAll(DB_GAMES.drop(1))
            }
            val minReleaseDateTimestamp = 300L

            sut.saveGames(gamesToSave)

            val expectedGames = gamesToSave
                .filter { it.releaseDate != null }
                .filter { it.releaseDate != null && it.releaseDate!! > minReleaseDateTimestamp }
                .sortedByDescending(DbGame::hypeCount)

            sut.observeMostAnticipatedGames(
                minReleaseDateTimestamp = minReleaseDateTimestamp,
                offset = 0,
                limit = gamesToSave.size
            ).test {
                assertThat(awaitItem()).isEqualTo(expectedGames)
            }
        }
    }

    @Test
    fun saves_most_anticipated_games_and_observes_most_anticipated_games_that_have_hype_count() {
        runTest {
            val gamesToSave = buildList {
                add(DB_GAMES[0].copy(releaseDate = null))
                add(DB_GAMES[1].copy(hypeCount = null))
                addAll(DB_GAMES.drop(2))
            }
            val minReleaseDateTimestamp = 300L

            sut.saveGames(gamesToSave)

            val expectedGames = gamesToSave
                .filter { it.releaseDate != null }
                .filter { it.releaseDate != null && it.releaseDate!! > minReleaseDateTimestamp }
                .filter { it.hypeCount != null }
                .sortedByDescending(DbGame::hypeCount)

            sut.observeMostAnticipatedGames(
                minReleaseDateTimestamp = minReleaseDateTimestamp,
                offset = 0,
                limit = gamesToSave.size
            ).test {
                assertThat(awaitItem()).isEqualTo(expectedGames)
            }
        }
    }
}

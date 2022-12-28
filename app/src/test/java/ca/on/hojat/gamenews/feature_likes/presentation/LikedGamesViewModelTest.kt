package ca.on.hojat.gamenews.feature_likes.presentation

import app.cash.turbine.test
import ca.on.hojat.gamenews.core.common_ui.base.events.GeneralCommand
import ca.on.hojat.gamenews.core.common_ui.widgets.FiniteUiState
import ca.on.hojat.gamenews.core.common_ui.widgets.games.GameUiModel
import ca.on.hojat.gamenews.core.common_ui.widgets.games.GameUiModelMapper
import ca.on.hojat.gamenews.core.common_ui.widgets.games.finiteUiState
import ca.on.hojat.gamenews.core.domain.entities.Game
import ca.on.hojat.gamenews.feature_likes.domain.ObserveLikedGamesUseCase
import ca.on.hojat.gamenews.core.common_testing.FakeErrorMapper
import ca.on.hojat.gamenews.core.common_testing.FakeLogger
import ca.on.hojat.gamenews.core.common_testing.FakeStringProvider
import ca.on.hojat.gamenews.core.common_testing.domain.DOMAIN_GAMES
import ca.on.hojat.gamenews.core.common_testing.domain.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

internal class LikedGamesViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(StandardTestDispatcher())

    private val observeLikedGamesUseCase = mockk<ObserveLikedGamesUseCase>(relaxed = true)

    private val logger = FakeLogger()
    private val sut by lazy {
        LikedGamesViewModel(
            observeLikedGamesUseCase = observeLikedGamesUseCase,
            uiModelMapper = FakeGameUiModelMapper(),
            dispatcherProvider = mainCoroutineRule.dispatcherProvider,
            stringProvider = FakeStringProvider(),
            errorMapper = FakeErrorMapper(),
            logger = logger,
        )
    }

    @Test
    fun `Emits correct ui states when loading data`() {
        runTest {
            every { observeLikedGamesUseCase.execute(any()) } returns flowOf(DOMAIN_GAMES)

            sut.uiState.test {
                val emptyState = awaitItem()
                val loadingState = awaitItem()
                val resultState = awaitItem()

                assertThat(emptyState.finiteUiState).isEqualTo(FiniteUiState.Empty)
                assertThat(loadingState.finiteUiState).isEqualTo(FiniteUiState.Loading)
                assertThat(resultState.finiteUiState).isEqualTo(FiniteUiState.Success)
                assertThat(resultState.games).hasSize(DOMAIN_GAMES.size)
            }
        }
    }

    @Test
    fun `Logs error when liked games loading fails`() {
        runTest {
            every { observeLikedGamesUseCase.execute(any()) } returns flow {
                throw IllegalStateException(
                    "error"
                )
            }

            sut
            advanceUntilIdle()

            assertThat(logger.errorMessage).isNotEmpty()
        }
    }

    @Test
    fun `Dispatches toast showing command when liked games loading fails`() {
        runTest {
            every { observeLikedGamesUseCase.execute(any()) } returns flow {
                throw IllegalStateException(
                    "error"
                )
            }

            sut.commandFlow.test {
                assertThat(awaitItem()).isInstanceOf(GeneralCommand.ShowLongToast::class.java)
            }
        }
    }

    @Test
    fun `Routes to search screen when search button is clicked`() {
        runTest {
            sut.routeFlow.test {
                sut.onSearchButtonClicked()

                assertThat(awaitItem()).isInstanceOf(LikedGamesRoute.Search::class.java)
            }
        }
    }

    @Test
    fun `Routes to info screen when game is clicked`() {
        runTest {
            val game = GameUiModel(
                id = 1,
                coverImageUrl = null,
                name = "",
                releaseDate = "",
                developerName = null,
                description = null,
            )

            sut.routeFlow.test {
                sut.onGameClicked(game)

                val route = awaitItem()

                assertThat(route).isInstanceOf(LikedGamesRoute.Info::class.java)
                assertThat((route as LikedGamesRoute.Info).gameId).isEqualTo(game.id)
            }
        }
    }

    private class FakeGameUiModelMapper : GameUiModelMapper {

        override fun mapToUiModel(game: Game): GameUiModel {
            return GameUiModel(
                id = game.id,
                coverImageUrl = null,
                name = game.name,
                releaseDate = "release_date",
                developerName = null,
                description = null,
            )
        }
    }
}

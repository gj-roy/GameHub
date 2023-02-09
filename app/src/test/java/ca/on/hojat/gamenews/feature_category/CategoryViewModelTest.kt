package ca.on.hojat.gamenews.feature_category

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import ca.on.hojat.gamenews.common_ui.widgets.FiniteUiState
import ca.on.hojat.gamenews.feature_category.di.GamesCategoryKey
import ca.on.hojat.gamenews.feature_category.widgets.GameCategoryUiModel
import ca.on.hojat.gamenews.feature_category.widgets.GameCategoryUiModelMapper
import ca.on.hojat.gamenews.feature_category.widgets.finiteUiState
import ca.on.hojat.gamenews.core.domain.entities.Game
import ca.on.hojat.gamenews.core.domain.games.usecases.ObservePopularGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.usecases.RefreshPopularGamesUseCase
import ca.on.hojat.gamenews.core.common_testing.FakeErrorMapper
import ca.on.hojat.gamenews.core.common_testing.FakeLogger
import ca.on.hojat.gamenews.core.common_testing.FakeStringProvider
import ca.on.hojat.gamenews.core.common_testing.domain.DOMAIN_GAMES
import ca.on.hojat.gamenews.core.common_testing.domain.MainCoroutineRule
import ca.on.hojat.gamenews.common_ui.base.events.GeneralCommand
import com.github.michaelbull.result.Ok
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import javax.inject.Provider

internal class CategoryViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(StandardTestDispatcher())

    private val observePopularGamesUseCase = mockk<ObservePopularGamesUseCase>(relaxed = true)
    private val refreshPopularGamesUseCase = mockk<RefreshPopularGamesUseCase>(relaxed = true)

    private val logger = FakeLogger()
    private val sut by lazy {
        CategoryViewModel(
            savedStateHandle = setupSavedStateHandle(),
            stringProvider = FakeStringProvider(),
            transitionAnimationDuration = 0L,
            useCases = setupUseCases(),
            uiModelMapper = FakeGameCategoryUiModelMapper(),
            dispatcherProvider = mainCoroutineRule.dispatcherProvider,
            errorMapper = FakeErrorMapper()
        )
    }

    private fun setupSavedStateHandle(): SavedStateHandle {
        return mockk(relaxed = true) {
            every { get<String>(any()) } returns CategoryType.POPULAR.name
        }
    }

    private fun setupUseCases(): CategoryUseCases {
        return CategoryUseCases(
            observeGamesUseCasesMap = mapOf(
                GamesCategoryKey.Type.POPULAR to Provider { observePopularGamesUseCase },
                GamesCategoryKey.Type.RECENTLY_RELEASED to Provider(::mockk),
                GamesCategoryKey.Type.COMING_SOON to Provider(::mockk),
                GamesCategoryKey.Type.MOST_ANTICIPATED to Provider(::mockk)
            ),
            refreshGamesUseCasesMap = mapOf(
                GamesCategoryKey.Type.POPULAR to Provider { refreshPopularGamesUseCase },
                GamesCategoryKey.Type.RECENTLY_RELEASED to Provider(::mockk),
                GamesCategoryKey.Type.COMING_SOON to Provider(::mockk),
                GamesCategoryKey.Type.MOST_ANTICIPATED to Provider(::mockk)
            )
        )
    }

    @Test
    fun `Emits toolbar title when initialized`() {
        runTest {
            sut.uiState.test {
                assertThat(awaitItem().title).isNotEmpty()
            }
        }
    }

    @Test
    fun `Emits correct ui states when observing games`() {
        runTest {
            every { observePopularGamesUseCase.execute(any()) } returns flowOf(DOMAIN_GAMES)

            sut.uiState.test {
                assertThat(awaitItem().finiteUiState).isEqualTo(FiniteUiState.Empty)
                assertThat(awaitItem().finiteUiState).isEqualTo(FiniteUiState.Success)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `Dispatches toast showing command when games observing use case throws error`() {
        runTest {
            every { observePopularGamesUseCase.execute(any()) } returns flow {
                throw IllegalStateException(
                    "error"
                )
            }
            every { refreshPopularGamesUseCase.execute(any()) } returns flowOf(Ok(DOMAIN_GAMES))

            sut.commandFlow.test {
                assertThat(awaitItem()).isInstanceOf(GeneralCommand.ShowLongToast::class.java)
            }
        }
    }

    @Test
    fun `Emits correct ui states when refreshing games`() {
        runTest {
            every {
                refreshPopularGamesUseCase.execute(any())
            } returns flow {
                // Refresh, for some reason, emits way too fast.
                // Adding delay to grab all possible states.
                delay(10)
                emit(Ok(DOMAIN_GAMES))
            }

            sut.uiState.test {
                assertThat(awaitItem().finiteUiState).isEqualTo(FiniteUiState.Empty)
                assertThat(awaitItem().finiteUiState).isEqualTo(FiniteUiState.Loading)
                assertThat(awaitItem().finiteUiState).isEqualTo(FiniteUiState.Empty)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `Dispatches toast showing command when games refreshing use case throws error`() {
        runTest {
            every { observePopularGamesUseCase.execute(any()) } returns flowOf(DOMAIN_GAMES)
            every { refreshPopularGamesUseCase.execute(any()) } returns flow {
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
    fun `Routes to previous screen when toolbar left button is clicked`() {
        runTest {
            sut.routeFlow.test {
                sut.onToolbarLeftButtonClicked()

                assertThat(awaitItem()).isInstanceOf(CategoryScreenRoute.Back::class.java)
            }
        }
    }

    @Test
    fun `Routes to game info screen when game is clicked`() {
        runTest {
            val game = GameCategoryUiModel(
                id = 1,
                title = "title",
                coverUrl = null
            )

            sut.routeFlow.test {
                sut.onGameClicked(game)

                val route = awaitItem()

                assertThat(route).isInstanceOf(CategoryScreenRoute.Info::class.java)
                assertThat((route as CategoryScreenRoute.Info).gameId).isEqualTo(game.id)
            }
        }
    }

    private class FakeGameCategoryUiModelMapper : GameCategoryUiModelMapper {

        override fun mapToUiModel(game: Game): GameCategoryUiModel {
            return GameCategoryUiModel(
                id = game.id,
                title = game.name,
                coverUrl = null,
            )
        }
    }
}

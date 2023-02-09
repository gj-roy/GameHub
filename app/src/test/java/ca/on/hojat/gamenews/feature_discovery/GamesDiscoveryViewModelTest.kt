package ca.on.hojat.gamenews.feature_discovery

import app.cash.turbine.test
import ca.on.hojat.gamenews.feature_discovery.di.GamesDiscoveryKey
import ca.on.hojat.gamenews.feature_discovery.mapping.GamesDiscoveryItemGameUiModelMapper
import ca.on.hojat.gamenews.feature_discovery.widgets.DiscoverScreenItemData
import ca.on.hojat.gamenews.core.domain.entities.Game
import ca.on.hojat.gamenews.core.domain.games.usecases.ObservePopularGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.usecases.RefreshPopularGamesUseCase
import ca.on.hojat.gamenews.core.common_testing.FakeErrorMapper
import ca.on.hojat.gamenews.core.common_testing.FakeStringProvider
import ca.on.hojat.gamenews.core.common_testing.domain.DOMAIN_GAMES
import ca.on.hojat.gamenews.core.common_testing.domain.MainCoroutineRule
import ca.on.hojat.gamenews.common_ui.base.events.GeneralCommand
import com.github.michaelbull.result.Ok
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

internal class GamesDiscoveryViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(StandardTestDispatcher())

    private val observePopularGamesUseCase = mockk<ObservePopularGamesUseCase>(relaxed = true)
    private val refreshPopularGamesUseCase = mockk<RefreshPopularGamesUseCase>(relaxed = true)

    private val sut by lazy {
        GamesDiscoveryViewModel(
            useCases = setupUseCases(),
            uiModelMapper = FakeGamesDiscoveryItemGameUiModelMapper(),
            dispatcherProvider = mainCoroutineRule.dispatcherProvider,
            stringProvider = FakeStringProvider(),
            errorMapper = FakeErrorMapper()
        )
    }

    private fun setupUseCases(): GamesDiscoveryUseCases {
        return GamesDiscoveryUseCases(
            observeGamesUseCasesMap = mapOf(
                GamesDiscoveryKey.Type.POPULAR to observePopularGamesUseCase,
                GamesDiscoveryKey.Type.RECENTLY_RELEASED to mockk {
                    every { execute(any()) } returns flowOf(DOMAIN_GAMES)
                },
                GamesDiscoveryKey.Type.COMING_SOON to mockk {
                    every { execute(any()) } returns flowOf(DOMAIN_GAMES)
                },
                GamesDiscoveryKey.Type.MOST_ANTICIPATED to mockk {
                    every { execute(any()) } returns flowOf(DOMAIN_GAMES)
                }
            ),
            refreshGamesUseCasesMap = mapOf(
                GamesDiscoveryKey.Type.POPULAR to refreshPopularGamesUseCase,
                GamesDiscoveryKey.Type.RECENTLY_RELEASED to mockk {
                    every { execute(any()) } returns flowOf(Ok(DOMAIN_GAMES))
                },
                GamesDiscoveryKey.Type.COMING_SOON to mockk {
                    every { execute(any()) } returns flowOf(Ok(DOMAIN_GAMES))
                },
                GamesDiscoveryKey.Type.MOST_ANTICIPATED to mockk {
                    every { execute(any()) } returns flowOf(Ok(DOMAIN_GAMES))
                }
            )
        )
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
    fun `Routes to search screen when search button is clicked`() {
        runTest {
            sut.routeFlow.test {
                sut.onSearchButtonClicked()

                assertThat(awaitItem()).isInstanceOf(GamesDiscoveryRoute.Search::class.java)
            }
        }
    }

    @Test
    fun `Routes to games category screen when more button is clicked`() {
        runTest {
            val categoryName = GamesDiscoveryCategory.POPULAR.name

            sut.routeFlow.test {
                sut.onCategoryMoreButtonClicked(categoryName)

                val route = awaitItem()

                assertThat(route).isInstanceOf(GamesDiscoveryRoute.Category::class.java)
                assertThat((route as GamesDiscoveryRoute.Category).category).isEqualTo(categoryName)
            }
        }
    }

    @Test
    fun `Routes to game info screen when game is clicked`() {
        runTest {
            val item = DiscoverScreenItemData(
                id = 1,
                title = "title",
                coverUrl = null
            )

            sut.routeFlow.test {
                sut.onCategoryGameClicked(item)

                val route = awaitItem()

                assertThat(route).isInstanceOf(GamesDiscoveryRoute.Info::class.java)
                assertThat((route as GamesDiscoveryRoute.Info).gameId).isEqualTo(item.id)
            }
        }
    }

    private class FakeGamesDiscoveryItemGameUiModelMapper : GamesDiscoveryItemGameUiModelMapper {

        override fun mapToUiModel(game: Game): DiscoverScreenItemData {
            return DiscoverScreenItemData(
                id = game.id,
                title = game.name,
                coverUrl = null
            )
        }
    }
}

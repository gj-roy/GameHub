
package ca.on.hojat.gamenews.core.domain.games

import app.cash.turbine.test
import ca.on.hojat.gamenews.core.domain.games.common.ObserveUseCaseParams
import ca.on.hojat.gamenews.core.domain.games.repository.GamesLocalDataSource
import ca.on.hojat.gamenews.core.domain.games.usecases.ObservePopularGamesUseCaseImpl
import ca.on.hojat.gamenews.core.common_testing.domain.DOMAIN_GAMES
import ca.on.hojat.gamenews.core.common_testing.domain.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class ObservePopularGamesUseCaseImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK private lateinit var gamesLocalDataSource: GamesLocalDataSource

    private lateinit var sut: ObservePopularGamesUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        sut = ObservePopularGamesUseCaseImpl(
            gamesLocalDataSource = gamesLocalDataSource,
            dispatcherProvider = mainCoroutineRule.dispatcherProvider,
        )
    }

    @Test
    fun `Emits games successfully`() {
        runTest {
            every { gamesLocalDataSource.observePopularGames(any()) } returns flowOf(DOMAIN_GAMES)

            sut.execute(ObserveUseCaseParams()).test {
                assertThat(awaitItem()).isEqualTo(DOMAIN_GAMES)
                awaitComplete()
            }
        }
    }
}

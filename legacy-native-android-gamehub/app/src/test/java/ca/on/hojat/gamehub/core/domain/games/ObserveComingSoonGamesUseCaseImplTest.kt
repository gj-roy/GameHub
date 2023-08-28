package ca.on.hojat.gamehub.core.domain.games

import app.cash.turbine.test
import ca.on.hojat.gamehub.core.common_testing.domain.DOMAIN_GAMES
import ca.on.hojat.gamehub.core.common_testing.domain.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import ca.on.hojat.gamehub.core.domain.games.common.ObserveUseCaseParams
import ca.on.hojat.gamehub.core.domain.games.repository.GamesLocalDataSource
import ca.on.hojat.gamehub.core.domain.games.usecases.ObserveComingSoonGamesUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class ObserveComingSoonGamesUseCaseImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var gamesLocalDataSource: GamesLocalDataSource

    private lateinit var sut: ObserveComingSoonGamesUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        sut = ObserveComingSoonGamesUseCaseImpl(
            gamesLocalDataSource = gamesLocalDataSource,
            dispatcherProvider = mainCoroutineRule.dispatcherProvider,
        )
    }

    @Test
    fun `Emits games successfully`() {
        runTest {
            every { gamesLocalDataSource.observeComingSoonGames(any()) } returns flowOf(DOMAIN_GAMES)

            sut.execute(ObserveUseCaseParams()).test {
                assertThat(awaitItem()).isEqualTo(DOMAIN_GAMES)
                awaitComplete()
            }
        }
    }
}

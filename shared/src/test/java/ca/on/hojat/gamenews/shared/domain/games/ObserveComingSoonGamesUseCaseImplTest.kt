package ca.on.hojat.gamenews.shared.domain.games

import app.cash.turbine.test
import ca.on.hojat.gamenews.shared.testing.domain.DOMAIN_GAMES
import ca.on.hojat.gamenews.shared.testing.domain.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import ca.on.hojat.gamenews.core.domain.games.common.ObserveGamesUseCaseParams
import ca.on.hojat.gamenews.core.domain.games.datastores.GamesLocalDataStore
import ca.on.hojat.gamenews.core.domain.games.usecases.ObserveComingSoonGamesUseCaseImpl
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
    private lateinit var gamesLocalDataStore: GamesLocalDataStore

    private lateinit var sut: ObserveComingSoonGamesUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        sut = ObserveComingSoonGamesUseCaseImpl(
            gamesLocalDataStore = gamesLocalDataStore,
            dispatcherProvider = mainCoroutineRule.dispatcherProvider,
        )
    }

    @Test
    fun `Emits games successfully`() {
        runTest {
            every { gamesLocalDataStore.observeComingSoonGames(any()) } returns flowOf(DOMAIN_GAMES)

            sut.execute(ObserveGamesUseCaseParams()).test {
                assertThat(awaitItem()).isEqualTo(DOMAIN_GAMES)
                awaitComplete()
            }
        }
    }
}

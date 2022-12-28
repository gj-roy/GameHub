package ca.on.hojat.gamenews.feature_info.domain

import app.cash.turbine.test
import ca.on.hojat.gamenews.feature_info.GET_GAME_USE_CASE_PARAMS
import ca.on.hojat.gamenews.core.domain.games.datastores.GamesLocalDataStore
import ca.on.hojat.gamenews.core.common_testing.domain.DOMAIN_GAME
import ca.on.hojat.gamenews.core.common_testing.domain.MainCoroutineRule
import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import com.google.common.truth.Truth.assertThat
import ca.on.hojat.gamenews.feature_info.domain.usecases.GetGameUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class GetGameUseCaseImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var gamesLocalDataStore: GamesLocalDataStore

    private lateinit var sut: GetGameUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        sut = GetGameUseCaseImpl(
            gamesLocalDataStore = gamesLocalDataStore,
            dispatcherProvider = mainCoroutineRule.dispatcherProvider,
        )
    }

    @Test
    fun `Emits game successfully`() {
        runTest {
            coEvery { gamesLocalDataStore.getGame(any()) } returns DOMAIN_GAME

            sut.execute(GET_GAME_USE_CASE_PARAMS).test {
                assertThat(awaitItem().get()).isEqualTo(DOMAIN_GAME)
                awaitComplete()
            }
        }
    }

    @Test
    fun `Emits not found error if game ID does not reference existing game`() {
        runTest {
            coEvery { gamesLocalDataStore.getGame(any()) } returns null

            sut.execute(GET_GAME_USE_CASE_PARAMS).test {
                assertThat(awaitItem().getError() is Error.NotFound).isTrue()
                awaitComplete()
            }
        }
    }
}

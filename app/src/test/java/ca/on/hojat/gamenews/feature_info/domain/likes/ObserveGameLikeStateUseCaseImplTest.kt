package ca.on.hojat.gamenews.feature_info.domain.likes

import app.cash.turbine.test
import ca.on.hojat.gamenews.feature_info.OBSERVE_GAME_LIKE_STATE_USE_CASE_PARAMS
import ca.on.hojat.gamenews.core.domain.games.datastores.LikedGamesLocalDataStore
import ca.on.hojat.gamenews.shared.testing.domain.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import ca.on.hojat.gamenews.feature_info.domain.usecases.likes.ObserveGameLikeStateUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class ObserveGameLikeStateUseCaseImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var likedGamesLocalDataStore: LikedGamesLocalDataStore

    private lateinit var sut: ObserveGameLikeStateUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        sut = ObserveGameLikeStateUseCaseImpl(
            likedGamesLocalDataStore = likedGamesLocalDataStore,
            dispatcherProvider = mainCoroutineRule.dispatcherProvider,
        )
    }

    @Test
    fun `Emits game like state successfully`() {
        runTest {
            every { likedGamesLocalDataStore.observeGameLikeState(any()) } returns flowOf(true)
            sut.execute(OBSERVE_GAME_LIKE_STATE_USE_CASE_PARAMS).test {
                assertThat(awaitItem()).isTrue()
                awaitComplete()
            }

            every { likedGamesLocalDataStore.observeGameLikeState(any()) } returns flowOf(false)
            sut.execute(OBSERVE_GAME_LIKE_STATE_USE_CASE_PARAMS).test {
                assertThat(awaitItem()).isFalse()
                awaitComplete()
            }
        }
    }
}

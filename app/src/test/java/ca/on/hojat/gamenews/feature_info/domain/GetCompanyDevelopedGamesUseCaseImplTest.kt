package ca.on.hojat.gamenews.feature_info.domain

import app.cash.turbine.test
import ca.on.hojat.gamenews.feature_info.GET_COMPANY_DEVELOPED_GAMES_USE_CASE_PARAMS
import ca.on.hojat.gamenews.shared.domain.games.datastores.GamesLocalDataStore
import ca.on.hojat.gamenews.shared.testing.domain.DOMAIN_GAMES
import ca.on.hojat.gamenews.shared.testing.domain.MainCoroutineRule
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.get
import com.google.common.truth.Truth.assertThat
import com.paulrybitskyi.gamedge.feature_info.domain.usecases.GetCompanyDevelopedGamesUseCaseImpl
import com.paulrybitskyi.gamedge.feature_info.domain.usecases.RefreshCompanyDevelopedGamesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class GetCompanyDevelopedGamesUseCaseImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var refreshCompanyDevelopedGamesUseCase: RefreshCompanyDevelopedGamesUseCase
    @MockK
    private lateinit var gamesLocalDataStore: GamesLocalDataStore

    private lateinit var sut: GetCompanyDevelopedGamesUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        sut = GetCompanyDevelopedGamesUseCaseImpl(
            refreshCompanyDevelopedGamesUseCase = refreshCompanyDevelopedGamesUseCase,
            gamesLocalDataStore = gamesLocalDataStore,
            dispatcherProvider = mainCoroutineRule.dispatcherProvider,
        )
    }

    @Test
    fun `Emits games that refresh use case successfully emits`() {
        runTest {
            coEvery { refreshCompanyDevelopedGamesUseCase.execute(any()) } returns flowOf(
                Ok(
                    DOMAIN_GAMES
                )
            )

            sut.execute(GET_COMPANY_DEVELOPED_GAMES_USE_CASE_PARAMS).test {
                assertThat(awaitItem().get()).isEqualTo(DOMAIN_GAMES)
                awaitComplete()
            }
        }
    }

    @Test
    fun `Emits games from local data store if refresh use case does not emit`() {
        runTest {
            coEvery { refreshCompanyDevelopedGamesUseCase.execute(any()) } returns flowOf()
            coEvery {
                gamesLocalDataStore.getCompanyDevelopedGames(
                    any(),
                    any()
                )
            } returns DOMAIN_GAMES

            sut.execute(GET_COMPANY_DEVELOPED_GAMES_USE_CASE_PARAMS).test {
                assertThat(awaitItem().get()).isEqualTo(DOMAIN_GAMES)
                awaitComplete()
            }
        }
    }
}

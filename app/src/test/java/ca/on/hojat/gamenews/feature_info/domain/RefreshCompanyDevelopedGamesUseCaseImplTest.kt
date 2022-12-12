package ca.on.hojat.gamenews.feature_info.domain

import app.cash.turbine.test
import ca.on.hojat.gamenews.feature_info.REFRESH_COMPANY_DEVELOPED_GAMES_USE_CASE_PARAMS
import ca.on.hojat.gamenews.feature_info.domain.usecases.RefreshCompanyDevelopedGamesUseCaseImpl
import ca.on.hojat.gamenews.shared.domain.games.common.throttling.GamesRefreshingThrottler
import ca.on.hojat.gamenews.shared.domain.games.common.throttling.GamesRefreshingThrottlerTools
import ca.on.hojat.gamenews.shared.domain.games.datastores.GamesDataStores
import ca.on.hojat.gamenews.shared.domain.games.datastores.GamesLocalDataStore
import ca.on.hojat.gamenews.shared.domain.games.datastores.GamesRemoteDataStore
import ca.on.hojat.gamenews.shared.testing.domain.DOMAIN_ERROR_UNKNOWN
import ca.on.hojat.gamenews.shared.testing.domain.DOMAIN_GAMES
import ca.on.hojat.gamenews.shared.testing.domain.FakeGamesRefreshingThrottlerKeyProvider
import ca.on.hojat.gamenews.shared.testing.domain.MainCoroutineRule
import ca.on.hojat.gamenews.shared.testing.domain.coVerifyNotCalled
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.get
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class RefreshCompanyDevelopedGamesUseCaseImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var gamesLocalDataStore: GamesLocalDataStore
    @MockK
    private lateinit var gamesRemoteDataStore: GamesRemoteDataStore
    @MockK
    private lateinit var throttler: GamesRefreshingThrottler

    private lateinit var sut: RefreshCompanyDevelopedGamesUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        sut = RefreshCompanyDevelopedGamesUseCaseImpl(
            gamesDataStores = GamesDataStores(
                local = gamesLocalDataStore,
                remote = gamesRemoteDataStore
            ),
            dispatcherProvider = mainCoroutineRule.dispatcherProvider,
            throttlerTools = GamesRefreshingThrottlerTools(
                throttler = throttler,
                keyProvider = FakeGamesRefreshingThrottlerKeyProvider()
            ),
        )
    }

    @Test
    fun `Emits remote games when refresh is possible`() {
        runTest {
            coEvery { throttler.canRefreshCompanyDevelopedGames(any()) } returns true
            coEvery { gamesRemoteDataStore.getCompanyDevelopedGames(any(), any()) } returns Ok(
                DOMAIN_GAMES
            )

            sut.execute(REFRESH_COMPANY_DEVELOPED_GAMES_USE_CASE_PARAMS).test {
                assertThat(awaitItem().get()).isEqualTo(DOMAIN_GAMES)
                awaitComplete()
            }
        }
    }

    @Test
    fun `Does not emit remote games when refresh is not possible`() {
        runTest {
            coEvery { throttler.canRefreshCompanyDevelopedGames(any()) } returns false

            sut.execute(REFRESH_COMPANY_DEVELOPED_GAMES_USE_CASE_PARAMS).test {
                awaitComplete()
            }
        }
    }

    @Test
    fun `Saves remote games into local data store when refresh is successful`() {
        runTest {
            coEvery { throttler.canRefreshCompanyDevelopedGames(any()) } returns true
            coEvery { gamesRemoteDataStore.getCompanyDevelopedGames(any(), any()) } returns Ok(
                DOMAIN_GAMES
            )

            sut.execute(REFRESH_COMPANY_DEVELOPED_GAMES_USE_CASE_PARAMS).firstOrNull()

            coVerify { gamesLocalDataStore.saveGames(DOMAIN_GAMES) }
        }
    }

    @Test
    fun `Does not save remote games into local data store when refresh is not possible`() {
        runTest {
            coEvery { throttler.canRefreshCompanyDevelopedGames(any()) } returns false

            sut.execute(REFRESH_COMPANY_DEVELOPED_GAMES_USE_CASE_PARAMS).firstOrNull()

            coVerifyNotCalled { gamesLocalDataStore.saveGames(any()) }
        }
    }

    @Test
    fun `Does not save remote games into local data store when refresh is unsuccessful`() {
        runTest {
            coEvery { throttler.canRefreshCompanyDevelopedGames(any()) } returns false
            coEvery { gamesRemoteDataStore.getCompanyDevelopedGames(any(), any()) } returns Err(
                DOMAIN_ERROR_UNKNOWN
            )

            sut.execute(REFRESH_COMPANY_DEVELOPED_GAMES_USE_CASE_PARAMS).firstOrNull()

            coVerifyNotCalled { gamesLocalDataStore.saveGames(any()) }
        }
    }

    @Test
    fun `Updates games last refresh time when refresh is successful`() {
        runTest {
            coEvery { throttler.canRefreshCompanyDevelopedGames(any()) } returns true
            coEvery { gamesRemoteDataStore.getCompanyDevelopedGames(any(), any()) } returns Ok(
                DOMAIN_GAMES
            )

            sut.execute(REFRESH_COMPANY_DEVELOPED_GAMES_USE_CASE_PARAMS).firstOrNull()

            coVerify { throttler.updateGamesLastRefreshTime(any()) }
        }
    }

    @Test
    fun `Does not update games last refresh time when refresh is not possible`() {
        runTest {
            coEvery { throttler.canRefreshCompanyDevelopedGames(any()) } returns false

            sut.execute(REFRESH_COMPANY_DEVELOPED_GAMES_USE_CASE_PARAMS).firstOrNull()

            coVerifyNotCalled { throttler.updateGamesLastRefreshTime(any()) }
        }
    }

    @Test
    fun `Does not update games last refresh time when refresh is unsuccessful`() {
        runTest {
            coEvery { throttler.canRefreshCompanyDevelopedGames(any()) } returns false
            coEvery { gamesRemoteDataStore.getCompanyDevelopedGames(any(), any()) } returns Err(
                DOMAIN_ERROR_UNKNOWN
            )

            sut.execute(REFRESH_COMPANY_DEVELOPED_GAMES_USE_CASE_PARAMS).firstOrNull()

            coVerifyNotCalled { throttler.updateGamesLastRefreshTime(any()) }
        }
    }
}

package ca.on.hojat.gamenews.feature_news.domain

import app.cash.turbine.test
import ca.on.hojat.gamenews.feature_news.DOMAIN_ARTICLES
import ca.on.hojat.gamenews.shared.testing.domain.MainCoroutineRule
import ca.on.hojat.gamenews.shared.testing.domain.PAGINATION
import com.google.common.truth.Truth.assertThat
import com.paulrybitskyi.gamedge.feature.news.domain.usecases.ObserveArticlesUseCase
import com.paulrybitskyi.gamedge.feature.news.domain.usecases.ObserveArticlesUseCaseImpl
import com.paulrybitskyi.gamedge.feature_news.domain.datastores.ArticlesLocalDataStore
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private val USE_CASE_PARAMS = ObserveArticlesUseCase.Params(PAGINATION)

internal class ObserveArticlesUseCaseImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var articlesLocalDataStore: ArticlesLocalDataStore

    private lateinit var SUT: ObserveArticlesUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        SUT = ObserveArticlesUseCaseImpl(
            articlesLocalDataStore = articlesLocalDataStore,
            dispatcherProvider = mainCoroutineRule.dispatcherProvider,
        )
    }

    @Test
    fun `Emits articles from local data store`() {
        runTest {
            every { articlesLocalDataStore.observeArticles(any()) } returns flowOf(DOMAIN_ARTICLES)

            SUT.execute(USE_CASE_PARAMS).test {
                assertThat(awaitItem()).isEqualTo(DOMAIN_ARTICLES)
                awaitComplete()
            }
        }
    }
}

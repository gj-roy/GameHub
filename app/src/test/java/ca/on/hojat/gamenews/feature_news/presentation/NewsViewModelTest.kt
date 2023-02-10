package ca.on.hojat.gamenews.feature_news.presentation

import app.cash.turbine.test
import ca.on.hojat.gamenews.common_ui.base.events.GeneralCommand
import ca.on.hojat.gamenews.common_ui.widgets.FiniteUiState
import ca.on.hojat.gamenews.core.common_testing.FakeErrorMapper
import ca.on.hojat.gamenews.core.common_testing.domain.MainCoroutineRule
import ca.on.hojat.gamenews.feature_news.DOMAIN_ARTICLES
import ca.on.hojat.gamenews.feature_news.domain.DomainArticle
import ca.on.hojat.gamenews.feature_news.domain.usecases.ObserveArticlesUseCase
import ca.on.hojat.gamenews.feature_news.domain.usecases.RefreshArticlesUseCase
import ca.on.hojat.gamenews.feature_news.presentation.mapping.NewsItemUiModelMapper
import ca.on.hojat.gamenews.feature_news.presentation.widgets.NewsItemUiModel
import com.github.michaelbull.result.Ok
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

internal class NewsViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(StandardTestDispatcher())

    private val observeArticlesUseCase = mockk<ObserveArticlesUseCase>(relaxed = true)
    private val refreshArticlesUseCase = mockk<RefreshArticlesUseCase>(relaxed = true)

    private val sut by lazy {
        NewsViewModel(
            observeArticlesUseCase = observeArticlesUseCase,
            refreshArticlesUseCase = refreshArticlesUseCase,
            uiModelMapper = FakeNewsItemUiModelMapper(),
            dispatcherProvider = mainCoroutineRule.dispatcherProvider,
            errorMapper = FakeErrorMapper(),
        )
    }

    @Test
    fun `Emits correct ui states when loading data`() {
        runTest {
            every { observeArticlesUseCase.execute(any()) } returns flowOf(DOMAIN_ARTICLES)

            sut.uiState.test {
                val emptyState = awaitItem()
                val loadingState = awaitItem()
                val resultState = awaitItem()

                assertThat(emptyState.finiteUiState).isEqualTo(FiniteUiState.Empty)
                assertThat(loadingState.finiteUiState).isEqualTo(FiniteUiState.Loading)
                assertThat(resultState.finiteUiState).isEqualTo(FiniteUiState.Success)
                assertThat(resultState.news).hasSize(DOMAIN_ARTICLES.size)
            }
        }
    }

    @Test
    fun `Dispatches toast showing command when articles observing use case throws error`() {
        runTest {
            every { observeArticlesUseCase.execute(any()) } returns flow {
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
    fun `Dispatches url opening command when clicking on news item`() {
        runTest {
            val itemModel = NewsItemUiModel(
                id = 1,
                imageUrl = null,
                title = "",
                lede = "",
                publicationDate = "",
                siteDetailUrl = "site_detail_url",
            )

            sut.commandFlow.test {
                sut.onNewsItemClicked(itemModel)

                val command = awaitItem()

                assertThat(command).isInstanceOf(NewsScreenCommand.OpenUrl::class.java)
                assertThat((command as NewsScreenCommand.OpenUrl).url).isEqualTo(itemModel.siteDetailUrl)
            }
        }
    }

    @Test
    fun `Emits correct ui states when refreshing data`() {
        runTest {
            every { refreshArticlesUseCase.execute(any()) } returns flowOf(Ok(DOMAIN_ARTICLES))

            sut
            advanceUntilIdle()

            sut.uiState.test {
                sut.onRefreshRequested()

                assertThat(awaitItem().isRefreshing).isFalse()
                assertThat(awaitItem().isRefreshing).isTrue()
                assertThat(awaitItem().isRefreshing).isFalse()
            }
        }
    }

    private class FakeNewsItemUiModelMapper : NewsItemUiModelMapper() {

        override fun mapToUiModel(article: DomainArticle): NewsItemUiModel {
            return NewsItemUiModel(
                id = article.id,
                imageUrl = null,
                title = article.title,
                lede = article.lede,
                publicationDate = "publication_date",
                siteDetailUrl = article.siteDetailUrl,
            )
        }
    }
}

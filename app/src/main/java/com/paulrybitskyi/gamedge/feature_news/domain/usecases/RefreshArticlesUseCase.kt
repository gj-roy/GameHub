package com.paulrybitskyi.gamedge.feature.news.domain.usecases

import ca.on.hojat.gamenews.shared.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.shared.domain.common.DomainResult
import ca.on.hojat.gamenews.shared.domain.common.entities.Pagination
import ca.on.hojat.gamenews.shared.domain.common.usecases.ObservableUseCase
import ca.on.hojat.gamenews.shared.domain.common.extensions.onEachSuccess
import com.paulrybitskyi.gamedge.feature_news.domain.datastores.ArticlesDataStores
import com.paulrybitskyi.gamedge.feature_news.domain.entities.Article
import com.paulrybitskyi.gamedge.feature.news.domain.throttling.ArticlesRefreshingThrottlerTools
import com.paulrybitskyi.gamedge.feature.news.domain.usecases.RefreshArticlesUseCase.Params
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

internal interface RefreshArticlesUseCase : ObservableUseCase<Params, DomainResult<List<Article>>> {

    data class Params(val pagination: Pagination = Pagination())
}

@Singleton
@BindType
internal class RefreshArticlesUseCaseImpl @Inject constructor(
    private val articlesDataStores: ArticlesDataStores,
    private val dispatcherProvider: DispatcherProvider,
    private val throttlerTools: ArticlesRefreshingThrottlerTools,
) : RefreshArticlesUseCase {

    override fun execute(params: Params): Flow<DomainResult<List<Article>>> {
        val throttlerKey = throttlerTools.keyProvider.provideArticlesKey(params.pagination)

        return flow {
            if (throttlerTools.throttler.canRefreshArticles(throttlerKey)) {
                emit(articlesDataStores.remote.getArticles(params.pagination))
            }
        }
            .onEachSuccess { articles ->
                articlesDataStores.local.saveArticles(articles)
                throttlerTools.throttler.updateArticlesLastRefreshTime(throttlerKey)
            }
            .flowOn(dispatcherProvider.main)
    }
}

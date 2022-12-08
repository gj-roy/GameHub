package com.paulrybitskyi.gamedge.feature.news.data.datastores.gamespot

import ca.on.hojat.gamenews.shared.api.common.ApiResult
import ca.on.hojat.gamenews.shared.api.gamespot.articles.ArticlesEndpoint
import ca.on.hojat.gamenews.shared.api.gamespot.articles.entities.ApiArticle
import ca.on.hojat.gamenews.shared.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.shared.domain.common.DomainResult
import ca.on.hojat.gamenews.shared.domain.common.entities.Pagination
import com.github.michaelbull.result.mapEither
import com.paulrybitskyi.gamedge.common.data.common.ApiErrorMapper
import com.paulrybitskyi.gamedge.feature.news.domain.datastores.ArticlesRemoteDataStore
import com.paulrybitskyi.gamedge.feature.news.domain.entities.Article
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@BindType
internal class ArticlesGamespotDataStore @Inject constructor(
    private val articlesEndpoint: ArticlesEndpoint,
    private val dispatcherProvider: DispatcherProvider,
    private val apiArticleMapper: GamespotArticleMapper,
    private val apiErrorMapper: ApiErrorMapper,
) : ArticlesRemoteDataStore {

    override suspend fun getArticles(pagination: Pagination): DomainResult<List<Article>> {
        return articlesEndpoint
            .getArticles(pagination.offset, pagination.limit)
            .toDataStoreResult()
    }

    private suspend fun ApiResult<List<ApiArticle>>.toDataStoreResult(): DomainResult<List<Article>> {
        return withContext(dispatcherProvider.computation) {
            mapEither(apiArticleMapper::mapToDomainArticles, apiErrorMapper::mapToDomainError)
        }
    }
}

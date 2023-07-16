package ca.on.hojat.gamenews.feature_news.data.datasources.gamespot

import ca.on.hojat.gamenews.core.data.api.ApiErrorMapper
import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.core.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.entities.Pagination
import ca.on.hojat.gamenews.feature_news.domain.repository.ArticlesRemoteDataSource
import ca.on.hojat.gamenews.feature_news.domain.entities.Article
import ca.on.hojat.gamenews.core.data.api.common.ApiResult
import ca.on.hojat.gamenews.core.data.api.gamespot.articles.ArticlesEndpoint
import ca.on.hojat.gamenews.core.data.api.gamespot.articles.entities.ApiArticle
import com.github.michaelbull.result.mapEither
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@BindType
internal class ArticlesGamespotDataSource @Inject constructor(
    private val articlesEndpoint: ArticlesEndpoint,
    private val dispatcherProvider: DispatcherProvider,
    private val apiArticleMapper: GamespotArticleMapper,
    private val apiErrorMapper: ApiErrorMapper,
) : ArticlesRemoteDataSource {

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

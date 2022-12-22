package ca.on.hojat.gamenews.feature_news.data.datastores.gamespot

import ca.on.hojat.gamenews.shared.api.common.ApiResult
import ca.on.hojat.gamenews.shared.api.gamespot.articles.ArticlesEndpoint
import ca.on.hojat.gamenews.shared.api.gamespot.articles.entities.ApiArticle
import ca.on.hojat.gamenews.core.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.core.domain.entities.Pagination
import com.github.michaelbull.result.mapEither
import ca.on.hojat.gamenews.shared.data.common.ApiErrorMapper
import ca.on.hojat.gamenews.feature_news.domain.datastores.ArticlesRemoteDataStore
import ca.on.hojat.gamenews.feature_news.domain.entities.Article
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

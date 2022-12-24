package ca.on.hojat.gamenews.shared.api.gamespot.articles

import ca.on.hojat.gamenews.shared.api.common.ApiResult
import ca.on.hojat.gamenews.shared.api.gamespot.articles.entities.ApiArticle
import ca.on.hojat.gamenews.shared.api.gamespot.common.GamespotQueryParamsFactory
import ca.on.hojat.gamenews.shared.api.gamespot.common.QUERY_PARAM_LIMIT
import ca.on.hojat.gamenews.shared.api.gamespot.common.QUERY_PARAM_OFFSET
import ca.on.hojat.gamenews.shared.api.gamespot.common.Response
import com.github.michaelbull.result.map
import ca.on.hojat.gamenews.core.data.api.common.Error
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject
import javax.inject.Singleton

interface ArticlesEndpoint {
    suspend fun getArticles(offset: Int, limit: Int): ApiResult<List<ApiArticle>>
}

@Singleton
@BindType
internal class ArticlesEndpointImpl @Inject constructor(
    private val articlesService: ArticlesService,
    private val queryParamsFactory: GamespotQueryParamsFactory
) : ArticlesEndpoint {

    override suspend fun getArticles(offset: Int, limit: Int): ApiResult<List<ApiArticle>> {
        val queryParams = queryParamsFactory.createArticlesQueryParams {
            put(QUERY_PARAM_OFFSET, offset.toString())
            put(QUERY_PARAM_LIMIT, limit.toString())
        }

        return articlesService.getArticles(queryParams)
            .map(Response<ApiArticle>::results)
    }
}

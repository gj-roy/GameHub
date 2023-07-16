package ca.on.hojat.gamenews.feature_news.domain.repository

import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.core.domain.entities.Pagination
import ca.on.hojat.gamenews.feature_news.domain.entities.Article

internal interface ArticlesRemoteDataSource {
    suspend fun getArticles(pagination: Pagination): DomainResult<List<Article>>
}

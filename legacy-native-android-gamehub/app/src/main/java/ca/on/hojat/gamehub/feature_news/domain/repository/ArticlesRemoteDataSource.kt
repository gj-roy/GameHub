package ca.on.hojat.gamehub.feature_news.domain.repository

import ca.on.hojat.gamehub.core.domain.DomainResult
import ca.on.hojat.gamehub.core.domain.entities.Pagination
import ca.on.hojat.gamehub.feature_news.domain.entities.Article

internal interface ArticlesRemoteDataSource {
    suspend fun getArticles(pagination: Pagination): DomainResult<List<Article>>
}

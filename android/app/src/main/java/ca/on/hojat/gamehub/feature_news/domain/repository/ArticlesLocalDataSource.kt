package ca.on.hojat.gamehub.feature_news.domain.repository

import ca.on.hojat.gamehub.core.domain.entities.Pagination
import ca.on.hojat.gamehub.feature_news.domain.entities.Article
import kotlinx.coroutines.flow.Flow

internal interface ArticlesLocalDataSource {
    suspend fun saveArticles(articles: List<Article>)
    fun observeArticles(pagination: Pagination): Flow<List<Article>>
}

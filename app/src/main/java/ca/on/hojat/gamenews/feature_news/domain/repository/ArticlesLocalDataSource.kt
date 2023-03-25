package ca.on.hojat.gamenews.feature_news.domain.repository

import ca.on.hojat.gamenews.core.domain.entities.Pagination
import ca.on.hojat.gamenews.feature_news.domain.entities.Article
import kotlinx.coroutines.flow.Flow

internal interface ArticlesLocalDataSource {
    suspend fun saveArticles(articles: List<Article>)
    fun observeArticles(pagination: Pagination): Flow<List<Article>>
}

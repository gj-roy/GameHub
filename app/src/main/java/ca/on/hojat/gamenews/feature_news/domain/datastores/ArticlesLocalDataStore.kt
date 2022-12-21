package ca.on.hojat.gamenews.feature_news.domain.datastores

import ca.on.hojat.gamenews.core.domain.entities.Pagination
import ca.on.hojat.gamenews.feature_news.domain.entities.Article
import kotlinx.coroutines.flow.Flow

internal interface ArticlesLocalDataStore {
    suspend fun saveArticles(articles: List<Article>)
    fun observeArticles(pagination: Pagination): Flow<List<Article>>
}

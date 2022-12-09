package com.paulrybitskyi.gamedge.feature_news.data.datastores.database

import ca.on.hojat.gamenews.shared.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.shared.domain.common.entities.Pagination
import com.paulrybitskyi.gamedge.database.articles.DatabaseArticle
import com.paulrybitskyi.gamedge.database.articles.tables.ArticlesTable
import com.paulrybitskyi.gamedge.feature_news.domain.datastores.ArticlesLocalDataStore
import com.paulrybitskyi.gamedge.feature_news.domain.entities.Article
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@BindType
internal class ArticlesDatabaseDataStore @Inject constructor(
    private val articlesTable: ArticlesTable,
    private val dispatcherProvider: DispatcherProvider,
    private val dbArticleMapper: DbArticleMapper
) : ArticlesLocalDataStore {

    override suspend fun saveArticles(articles: List<Article>) {
        articlesTable.saveArticles(
            withContext(dispatcherProvider.computation) {
                dbArticleMapper.mapToDatabaseArticles(articles)
            }
        )
    }

    override fun observeArticles(pagination: Pagination): Flow<List<Article>> {
        return articlesTable.observeArticles(
            offset = pagination.offset,
            limit = pagination.limit
        )
            .toDataArticlesFlow()
    }

    private fun Flow<List<DatabaseArticle>>.toDataArticlesFlow(): Flow<List<Article>> {
        return distinctUntilChanged()
            .map(dbArticleMapper::mapToDomainArticles)
            .flowOn(dispatcherProvider.computation)
    }
}

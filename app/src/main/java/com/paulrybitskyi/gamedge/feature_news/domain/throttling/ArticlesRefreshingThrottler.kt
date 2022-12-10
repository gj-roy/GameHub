package com.paulrybitskyi.gamedge.feature_news.domain.throttling

internal interface ArticlesRefreshingThrottler {
    suspend fun canRefreshArticles(key: String): Boolean
    suspend fun updateArticlesLastRefreshTime(key: String)
}

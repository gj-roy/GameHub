package ca.on.hojat.gamenews.feature_news.domain.repository

import javax.inject.Inject

internal class ArticlesRepository @Inject constructor(
    val local: ArticlesLocalDataSource,
    val remote: ArticlesRemoteDataSource
)

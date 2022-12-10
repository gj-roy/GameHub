package com.paulrybitskyi.gamedge.feature_news.domain.throttling

import com.paulrybitskyi.gamedge.feature_news.domain.throttling.ArticlesRefreshingThrottler
import com.paulrybitskyi.gamedge.feature_news.domain.throttling.ArticlesRefreshingThrottlerKeyProvider
import javax.inject.Inject

internal class ArticlesRefreshingThrottlerTools @Inject constructor(
    val throttler: ArticlesRefreshingThrottler,
    val keyProvider: ArticlesRefreshingThrottlerKeyProvider,
)

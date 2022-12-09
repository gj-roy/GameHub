package ca.on.hojat.gamenews.feature_news

import com.paulrybitskyi.gamedge.feature.news.domain.DomainArticle

internal val DOMAIN_ARTICLE = DomainArticle(
    id = 1,
    title = "title",
    lede = "lede",
    imageUrls = emptyMap(),
    publicationDate = 500L,
    siteDetailUrl = "site_detail_url",
)
internal val DOMAIN_ARTICLES = listOf(
    DOMAIN_ARTICLE.copy(id = 1),
    DOMAIN_ARTICLE.copy(id = 2),
    DOMAIN_ARTICLE.copy(id = 3),
)

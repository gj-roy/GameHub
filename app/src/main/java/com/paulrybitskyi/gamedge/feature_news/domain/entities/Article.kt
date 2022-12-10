package com.paulrybitskyi.gamedge.feature_news.domain.entities

internal data class Article(
    val id: Int,
    val title: String,
    val lede: String,
    val imageUrls: Map<ImageType, String>,
    val publicationDate: Long,
    val siteDetailUrl: String
)

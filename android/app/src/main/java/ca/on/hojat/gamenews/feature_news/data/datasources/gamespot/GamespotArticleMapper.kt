package ca.on.hojat.gamenews.feature_news.data.datasources.gamespot

import ca.on.hojat.gamenews.feature_news.domain.DomainArticle
import ca.on.hojat.gamenews.feature_news.domain.DomainImageType
import ca.on.hojat.gamenews.core.data.api.gamespot.articles.entities.ApiArticle
import ca.on.hojat.gamenews.core.data.api.gamespot.articles.entities.ApiImageType
import javax.inject.Inject

internal class GamespotArticleMapper @Inject constructor(
    private val publicationDateMapper: ArticlePublicationDateMapper,
) {
    private fun mapToDomainArticle(apiArticle: ApiArticle): DomainArticle {
        return DomainArticle(
            id = apiArticle.id,
            body = apiArticle.body,
            title = apiArticle.title,
            lede = apiArticle.lede,
            imageUrls = apiArticle.imageUrls.toDataImageUrls(),
            publicationDate = publicationDateMapper.mapToTimestamp(apiArticle.publicationDate),
            siteDetailUrl = apiArticle.siteDetailUrl
        )
    }

    private fun Map<ApiImageType, String>.toDataImageUrls(): Map<DomainImageType, String> {
        return mapKeys {
            DomainImageType.valueOf(it.key.name)
        }
    }

    internal fun mapToDomainArticles(apiArticles: List<ApiArticle>): List<DomainArticle> {
        return apiArticles.map(::mapToDomainArticle)
    }
}

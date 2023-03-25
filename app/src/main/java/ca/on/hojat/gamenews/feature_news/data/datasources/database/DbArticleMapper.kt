package ca.on.hojat.gamenews.feature_news.data.datasources.database

import ca.on.hojat.gamenews.core.data.database.articles.DbArticle
import ca.on.hojat.gamenews.core.data.database.articles.DbImageType
import ca.on.hojat.gamenews.feature_news.domain.DomainArticle
import ca.on.hojat.gamenews.feature_news.domain.DomainImageType
import javax.inject.Inject

internal class DbArticleMapper @Inject constructor() {

    fun mapToDatabaseArticle(dataArticle: DomainArticle): DbArticle {
        return DbArticle(
            id = dataArticle.id,
            body = dataArticle.body,
            title = dataArticle.title,
            lede = dataArticle.lede,
            imageUrls = dataArticle.imageUrls.toDatabaseImageUrls(),
            publicationDate = dataArticle.publicationDate,
            siteDetailUrl = dataArticle.siteDetailUrl
        )
    }

    private fun Map<DomainImageType, String>.toDatabaseImageUrls(): Map<DbImageType, String> {
        return mapKeys {
            DbImageType.valueOf(it.key.name)
        }
    }

    fun mapToDomainArticle(databaseArticle: DbArticle): DomainArticle {
        return DomainArticle(
            id = databaseArticle.id,
            body = databaseArticle.body,
            title = databaseArticle.title,
            lede = databaseArticle.lede,
            imageUrls = databaseArticle.imageUrls.toDomainImageUrls(),
            publicationDate = databaseArticle.publicationDate,
            siteDetailUrl = databaseArticle.siteDetailUrl
        )
    }

    private fun Map<DbImageType, String>.toDomainImageUrls(): Map<DomainImageType, String> {
        return mapKeys {
            DomainImageType.valueOf(it.key.name)
        }
    }
}

internal fun DbArticleMapper.mapToDatabaseArticles(dataArticles: List<DomainArticle>): List<DbArticle> {
    return dataArticles.map(::mapToDatabaseArticle)
}

internal fun DbArticleMapper.mapToDomainArticles(databaseArticles: List<DbArticle>): List<DomainArticle> {
    return databaseArticles.map(::mapToDomainArticle)
}

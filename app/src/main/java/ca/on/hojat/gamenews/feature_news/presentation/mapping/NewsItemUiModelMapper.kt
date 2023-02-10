package ca.on.hojat.gamenews.feature_news.presentation.mapping

import ca.on.hojat.gamenews.core.formatters.ArticlePublicationDateFormatter
import ca.on.hojat.gamenews.feature_news.domain.entities.Article
import ca.on.hojat.gamenews.feature_news.domain.entities.ImageType
import ca.on.hojat.gamenews.feature_news.presentation.widgets.GamingNewsItemUiModel
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

abstract class NewsItemUiModelMapper {
    internal abstract fun mapToUiModel(article: Article): GamingNewsItemUiModel

    /**
     * Just maps from the [Article] in our domain, to [GamingNewsItemUiModel] in UI layer.
     * we do this to make the data ready to be shown in composables.
     */
    internal fun mapToUiModels(
        articles: List<Article>,
    ): List<GamingNewsItemUiModel> {
        return articles.map(::mapToUiModel)
    }
}

@BindType(installIn = BindType.Component.VIEW_MODEL)
internal class NewsItemUiModelMapperImpl @Inject constructor(
    private val publicationDateFormatter: ArticlePublicationDateFormatter
) : NewsItemUiModelMapper() {

    override fun mapToUiModel(article: Article): GamingNewsItemUiModel {
        return GamingNewsItemUiModel(
            id = article.id,
            imageUrl = article.imageUrls[ImageType.ORIGINAL],
            title = article.title,
            lede = article.lede,
            publicationDate = article.formatPublicationDate(),
            siteDetailUrl = article.siteDetailUrl
        )
    }

    private fun Article.formatPublicationDate(): String {
        return publicationDateFormatter.formatPublicationDate(publicationDate)
    }
}

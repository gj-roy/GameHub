package ca.on.hojat.gamenews.feature_info.presentation.widgets.header.artworks

import ca.on.hojat.gamenews.core.factories.IgdbImageSize
import ca.on.hojat.gamenews.core.factories.IgdbImageUrlFactory
import ca.on.hojat.gamenews.core.domain.entities.Image
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

abstract class InfoScreenArtworkUiModelMapper {
    internal abstract fun mapToUiModel(image: Image): InfoScreenArtworkUiModel

    internal fun mapToUiModels(images: List<Image>): List<InfoScreenArtworkUiModel> {
        if (images.isEmpty()) return listOf(InfoScreenArtworkUiModel.DefaultImage)

        return images.map(::mapToUiModel)
            .filterIsInstance<InfoScreenArtworkUiModel.UrlImage>()
    }
}

@BindType(installIn = BindType.Component.VIEW_MODEL)
internal class InfoScreenArtworkUiModelMapperImpl @Inject constructor(
    private val igdbImageUrlFactory: IgdbImageUrlFactory,
) : InfoScreenArtworkUiModelMapper() {

    override fun mapToUiModel(image: Image): InfoScreenArtworkUiModel {
        return igdbImageUrlFactory.createUrl(
            image = image,
            config = IgdbImageUrlFactory.Config(IgdbImageSize.BIG_SCREENSHOT),
        )
            ?.let { url -> InfoScreenArtworkUiModel.UrlImage(id = image.id, url = url) }
            ?: InfoScreenArtworkUiModel.DefaultImage
    }
}

package ca.on.hojat.gamenews.feature_info.presentation.widgets.header.artworks

import ca.on.hojat.gamenews.core.factories.IgdbImageSize
import ca.on.hojat.gamenews.core.factories.IgdbImageUrlFactory
import ca.on.hojat.gamenews.core.domain.entities.Image
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

internal interface GameInfoArtworkUiModelMapper {
    fun mapToUiModel(image: Image): InfoScreenArtworkUiModel
}

@BindType(installIn = BindType.Component.VIEW_MODEL)
internal class GameInfoArtworkUiModelMapperImpl @Inject constructor(
    private val igdbImageUrlFactory: IgdbImageUrlFactory,
) : GameInfoArtworkUiModelMapper {

    override fun mapToUiModel(image: Image): InfoScreenArtworkUiModel {
        return igdbImageUrlFactory.createUrl(
            image = image,
            config = IgdbImageUrlFactory.Config(IgdbImageSize.BIG_SCREENSHOT),
        )
            ?.let { url -> InfoScreenArtworkUiModel.UrlImage(id = image.id, url = url) }
            ?: InfoScreenArtworkUiModel.DefaultImage
    }
}

internal fun GameInfoArtworkUiModelMapper.mapToUiModels(
    images: List<Image>,
): List<InfoScreenArtworkUiModel> {
    if (images.isEmpty()) return listOf(InfoScreenArtworkUiModel.DefaultImage)

    return images.map(::mapToUiModel)
        .filterIsInstance<InfoScreenArtworkUiModel.UrlImage>()
}

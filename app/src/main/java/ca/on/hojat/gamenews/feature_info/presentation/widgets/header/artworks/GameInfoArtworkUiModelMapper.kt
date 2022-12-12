package ca.on.hojat.gamenews.feature_info.presentation.widgets.header.artworks

import ca.on.hojat.gamenews.shared.core.factories.IgdbImageSize
import ca.on.hojat.gamenews.shared.core.factories.IgdbImageUrlFactory
import ca.on.hojat.gamenews.shared.domain.games.entities.Image
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

internal interface GameInfoArtworkUiModelMapper {
    fun mapToUiModel(image: Image): GameInfoArtworkUiModel
}

@BindType(installIn = BindType.Component.VIEW_MODEL)
internal class GameInfoArtworkUiModelMapperImpl @Inject constructor(
    private val igdbImageUrlFactory: IgdbImageUrlFactory,
) : GameInfoArtworkUiModelMapper {

    override fun mapToUiModel(image: Image): GameInfoArtworkUiModel {
        return igdbImageUrlFactory.createUrl(
            image = image,
            config = IgdbImageUrlFactory.Config(IgdbImageSize.BIG_SCREENSHOT),
        )
            ?.let { url -> GameInfoArtworkUiModel.UrlImage(id = image.id, url = url) }
            ?: GameInfoArtworkUiModel.DefaultImage
    }
}

internal fun GameInfoArtworkUiModelMapper.mapToUiModels(
    images: List<Image>,
): List<GameInfoArtworkUiModel> {
    if (images.isEmpty()) return listOf(GameInfoArtworkUiModel.DefaultImage)

    return images.map(::mapToUiModel)
        .filterIsInstance<GameInfoArtworkUiModel.UrlImage>()
}

package ca.on.hojat.gamenews.feature_info.presentation.widgets.screenshots

import ca.on.hojat.gamenews.core.factories.IgdbImageSize
import ca.on.hojat.gamenews.core.factories.IgdbImageUrlFactory
import ca.on.hojat.gamenews.core.domain.entities.Image
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

internal interface GameInfoScreenshotUiModelMapper {
    fun mapToUiModel(image: Image): GameInfoScreenshotUiModel?
}

@BindType(installIn = BindType.Component.VIEW_MODEL)
internal class GameInfoScreenshotUiModelMapperImpl @Inject constructor(
    private val igdbImageUrlFactory: IgdbImageUrlFactory,
) : GameInfoScreenshotUiModelMapper {

    override fun mapToUiModel(image: Image): GameInfoScreenshotUiModel? {
        val screenshotUrl = igdbImageUrlFactory.createUrl(
            image,
            IgdbImageUrlFactory.Config(IgdbImageSize.MEDIUM_SCREENSHOT),
        ) ?: return null

        return GameInfoScreenshotUiModel(
            id = image.id,
            url = screenshotUrl,
        )
    }
}

internal fun GameInfoScreenshotUiModelMapper.mapToUiModels(
    images: List<Image>,
): List<GameInfoScreenshotUiModel> {
    if (images.isEmpty()) return emptyList()

    return images.mapNotNull(::mapToUiModel)
}

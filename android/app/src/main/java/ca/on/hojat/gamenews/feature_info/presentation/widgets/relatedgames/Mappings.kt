package ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames

import ca.on.hojat.gamenews.presentation.widgets.categorypreview.GamesCategoryPreviewItemUiModel

internal fun List<RelatedGameUiModel>.mapToCategoryUiModels(): List<GamesCategoryPreviewItemUiModel> {
    return map {
        GamesCategoryPreviewItemUiModel(
            id = it.id,
            title = it.title,
            coverUrl = it.coverUrl
        )
    }
}

internal fun GamesCategoryPreviewItemUiModel.mapToInfoRelatedGameUiModel(): RelatedGameUiModel {
    return RelatedGameUiModel(
        id = id,
        title = title,
        coverUrl = coverUrl
    )
}

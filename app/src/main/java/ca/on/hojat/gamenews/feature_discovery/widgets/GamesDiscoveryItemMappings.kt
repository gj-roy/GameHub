package ca.on.hojat.gamenews.feature_discovery.widgets

import ca.on.hojat.gamenews.core.common_ui.widgets.categorypreview.GamesCategoryPreviewItemUiModel


internal fun List<GamesDiscoveryItemGameUiModel>.mapToCategoryUiModels(): List<GamesCategoryPreviewItemUiModel> {
    return map {
        GamesCategoryPreviewItemUiModel(
            id = it.id,
            title = it.title,
            coverUrl = it.coverUrl,
        )
    }
}

internal fun GamesCategoryPreviewItemUiModel.mapToDiscoveryUiModel(): GamesDiscoveryItemGameUiModel {
    return GamesDiscoveryItemGameUiModel(
        id = id,
        title = title,
        coverUrl = coverUrl,
    )
}

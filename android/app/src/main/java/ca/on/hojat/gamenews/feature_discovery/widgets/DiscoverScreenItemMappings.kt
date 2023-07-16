package ca.on.hojat.gamenews.feature_discovery.widgets

import ca.on.hojat.gamenews.presentation.widgets.categorypreview.GamesCategoryPreviewItemUiModel


internal fun List<DiscoverScreenItemData>.mapToCategoryUiModels(): List<GamesCategoryPreviewItemUiModel> {
    return map {
        GamesCategoryPreviewItemUiModel(
            id = it.id,
            title = it.title,
            coverUrl = it.coverUrl,
        )
    }
}

internal fun GamesCategoryPreviewItemUiModel.mapToDiscoveryUiModel(): DiscoverScreenItemData {
    return DiscoverScreenItemData(
        id = id,
        title = title,
        coverUrl = coverUrl,
    )
}

package ca.on.hojat.gamenews.feature_info.presentation.widgets.header.artworks

import androidx.compose.runtime.Immutable

private const val DEFAULT_IMAGE_ID = "default_image_id"

@Immutable
internal sealed class InfoScreenArtworkUiModel {
    object DefaultImage : InfoScreenArtworkUiModel()
    data class UrlImage(val id: String, val url: String) : InfoScreenArtworkUiModel()
}

internal val InfoScreenArtworkUiModel.id: String
    get() = when (this) {
        is InfoScreenArtworkUiModel.DefaultImage -> DEFAULT_IMAGE_ID
        is InfoScreenArtworkUiModel.UrlImage -> id
    }

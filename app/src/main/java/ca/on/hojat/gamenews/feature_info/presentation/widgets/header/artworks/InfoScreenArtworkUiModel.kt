package ca.on.hojat.gamenews.feature_info.presentation.widgets.header.artworks

import androidx.compose.runtime.Immutable

private const val DEFAULT_IMAGE_ID = "default_image_id"

@Immutable
internal sealed class InfoScreenArtworkUiModel(open val id: String) {
    object DefaultImage : InfoScreenArtworkUiModel(id = DEFAULT_IMAGE_ID)
    data class UrlImage(override val id: String, val url: String) :
        InfoScreenArtworkUiModel(id)
}

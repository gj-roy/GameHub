package com.paulrybitskyi.gamedge.feature_image_viewer

import androidx.compose.runtime.Immutable

@Immutable
internal data class ImageViewerUiState(
    val toolbarTitle: String,
    val imageUrls: List<String>,
    val selectedImageUrlIndex: Int,
)

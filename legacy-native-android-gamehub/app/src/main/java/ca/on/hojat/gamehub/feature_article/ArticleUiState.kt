package ca.on.hojat.gamehub.feature_article

import androidx.compose.runtime.Immutable

@Immutable
internal data class ArticleUiState(
    val imageUrl: String?,
    val title: String,
    val lede: String,
    val publicationDate: String,
    val articleUrl: String,
    val body: String
)

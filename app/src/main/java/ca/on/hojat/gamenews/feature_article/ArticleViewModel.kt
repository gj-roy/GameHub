package ca.on.hojat.gamenews.feature_article

import androidx.lifecycle.SavedStateHandle
import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.common_ui.base.BaseViewModel
import ca.on.hojat.gamenews.core.providers.StringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

private const val PARAM_IMAGE_URL = "image-url"
private const val PARAM_TITLE = "title"
private const val PARAM_LEDE = "lede"
private const val PARAM_PUBLICATION_DATE = "publication-date"
private const val PARAM_ARTICLE_URL = "article-url"
private const val PARAM_BODY= "body"

@HiltViewModel
internal class ArticleViewModel @Inject constructor(
    private val stringProvider: StringProvider,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(createInitialUiState())

    private val currentUiState: ArticleUiState
        get() = _uiState.value

    val uiState: StateFlow<ArticleUiState> = _uiState.asStateFlow()

    private fun createInitialUiState(): ArticleUiState {
        return ArticleUiState(
            imageUrl = savedStateHandle.get<String>(PARAM_IMAGE_URL),
            title = savedStateHandle.get<String>(PARAM_TITLE) ?: "",
            lede = savedStateHandle.get<String>(PARAM_LEDE) ?: "",
            publicationDate = savedStateHandle.get<String>(PARAM_PUBLICATION_DATE) ?: "",
            articleUrl = savedStateHandle.get<String>(PARAM_ARTICLE_URL) ?: "",
            body =savedStateHandle.get<String>(PARAM_BODY) ?: "",
        )
    }

    fun onShareButtonClicked() {
        val textToShare = stringProvider.getString(
            R.string.text_sharing_message_template,
            stringProvider.getString(R.string.news_article),
            currentUiState.articleUrl
        )

        dispatchCommand(ArticleCommand.ShareText(textToShare))
    }

    fun onBackPressed() {
        route(ArticleRoute.Back)
    }

}
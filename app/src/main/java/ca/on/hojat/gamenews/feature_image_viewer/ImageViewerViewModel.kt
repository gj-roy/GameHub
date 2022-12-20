package ca.on.hojat.gamenews.feature_image_viewer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.core.extensions.fromCsv
import ca.on.hojat.gamenews.core.providers.StringProvider
import ca.on.hojat.gamenews.shared.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal const val PARAM_TITLE = "title"
internal const val PARAM_INITIAL_POSITION = "initial-position"
internal const val PARAM_IMAGE_URLS = "image-urls"

internal const val KEY_SELECTED_POSITION = "selected_position"

@HiltViewModel
internal class ImageViewerViewModel @Inject constructor(
    private val stringProvider: StringProvider,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val title: String = savedStateHandle.get<String>(PARAM_TITLE)
        ?: stringProvider.getString(R.string.image_viewer_default_toolbar_title)

    private val _uiState = MutableStateFlow(createInitialUiState())

    private val currentUiState: ImageViewerUiState
        get() = _uiState.value

    val uiState: StateFlow<ImageViewerUiState> = _uiState.asStateFlow()

    init {

        _uiState.update {
            it.copy(
                selectedImageUrlIndex = getSelectedPosition(),
                imageUrls = parseImageUrls(),
            )
        }

        observeSelectedPositionChanges()
    }

    private fun createInitialUiState(): ImageViewerUiState {
        return ImageViewerUiState(
            toolbarTitle = "",
            imageUrls = emptyList(),
            selectedImageUrlIndex = 0,
        )
    }

    private fun getSelectedPosition(): Int {
        return savedStateHandle[KEY_SELECTED_POSITION]
            ?: checkNotNull(savedStateHandle.get<Int>(PARAM_INITIAL_POSITION))
    }

    private fun parseImageUrls(): List<String> {
        return savedStateHandle.get<String>(PARAM_IMAGE_URLS)
            ?.fromCsv()
            ?: error("No image urls provided.")
    }

    private fun observeSelectedPositionChanges() {
        uiState
            .map { it.selectedImageUrlIndex }
            .distinctUntilChanged()
            .onEach { selectedImageUrlIndex ->
                _uiState.update { it.copy(toolbarTitle = updateToolbarTitle()) }
                savedStateHandle[KEY_SELECTED_POSITION] = selectedImageUrlIndex
            }
            .launchIn(viewModelScope)
    }

    private fun updateToolbarTitle(): String {
        if (currentUiState.imageUrls.size == 1) return title

        return stringProvider.getString(
            R.string.image_viewer_toolbar_title_template,
            title,
            (currentUiState.selectedImageUrlIndex + 1),
            currentUiState.imageUrls.size
        )
    }

    fun onToolbarRightButtonClicked() {
        val currentImageUrl = currentUiState.imageUrls[currentUiState.selectedImageUrlIndex]
        val textToShare = stringProvider.getString(
            R.string.text_sharing_message_template,
            stringProvider.getString(R.string.image),
            currentImageUrl
        )

        dispatchCommand(ImageViewerCommand.ShareText(textToShare))
    }

    fun onImageChanged(imageIndex: Int) {
        _uiState.update { it.copy(selectedImageUrlIndex = imageIndex) }
    }

    fun onBackPressed() {
        route(ImageViewerRoute.Back)
    }
}

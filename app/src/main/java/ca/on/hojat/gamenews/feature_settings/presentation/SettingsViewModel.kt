package ca.on.hojat.gamenews.feature_settings.presentation

import androidx.lifecycle.viewModelScope
import ca.on.hojat.gamenews.core.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.common.usecases.execute
import ca.on.hojat.gamenews.common_ui.base.BaseViewModel
import ca.on.hojat.gamenews.feature_settings.domain.entities.Settings
import ca.on.hojat.gamenews.feature_settings.domain.entities.Theme
import ca.on.hojat.gamenews.feature_settings.domain.usecases.ObserveSettingsUseCase
import ca.on.hojat.gamenews.feature_settings.domain.usecases.SaveSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SettingsViewModel @Inject constructor(
    private val saveSettingsUseCase: SaveSettingsUseCase,
    private val observeSettingsUseCase: ObserveSettingsUseCase,
    private val uiModelMapper: SettingsUiModelMapper,
    private val dispatcherProvider: DispatcherProvider,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(createLoadingUiState())

    private val currentUiState: SettingsUiState
        get() = _uiState.value

    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        observeSettings()
    }

    private fun createLoadingUiState(): SettingsUiState {
        return SettingsUiState(
            isLoading = false,
            sections = emptyList(),
            selectedThemeName = null,
            isThemePickerVisible = false,
        )
            .toLoadingState()
    }

    private fun observeSettings() {
        observeSettingsUseCase.execute()
            .map { settings ->
                val sections = uiModelMapper.mapToUiModels(settings)
                val selectedThemeName = settings.theme.name

                sections to selectedThemeName
            }
            .flowOn(dispatcherProvider.computation)
            .map { (sections, selectedThemeName) ->
                currentUiState.toSuccessState(sections, selectedThemeName)
            }
            .onStart { emit(currentUiState.toLoadingState()) }
            .onEach { emittedUiState -> _uiState.update { emittedUiState } }
            .launchIn(viewModelScope)
    }

    fun onSettingClicked(item: SettingsSectionItemUiModel) {
        when (item.id) {
            SettingItem.THEME.id -> onThemeSettingClicked()
            SettingItem.SOURCE_CODE.id -> onSourceCodeSettingClicked()
            SettingItem.PRIVACY_POLICY.id -> onPrivacyPolicyClicked()
        }
    }

    private fun onThemeSettingClicked() {
        _uiState.update { it.copy(isThemePickerVisible = true) }
    }

    fun onThemePicked(theme: Theme) {
        onThemePickerDismissed()

        updateSettings { oldSettings ->
            oldSettings.copy(theme = theme)
        }
    }

    private fun updateSettings(newSettingsProvider: (old: Settings) -> Settings) {
        viewModelScope.launch {
            val oldSettings = observeSettingsUseCase.execute().first()
            val newSettings = newSettingsProvider(oldSettings)

            saveSettingsUseCase.execute(newSettings)
        }
    }

    fun onThemePickerDismissed() {
        _uiState.update { it.copy(isThemePickerVisible = false) }
    }

    private fun onSourceCodeSettingClicked() {
        dispatchCommand(SettingsCommand.OpenUrl(SOURCE_CODE_LINK))
    }

    private fun onPrivacyPolicyClicked() {
        dispatchCommand(SettingsCommand.OpenUrl(PRIVACY_POLICY_LINK))
    }

    companion object {
        const val SOURCE_CODE_LINK = "https://github.com/hojat72elect/Game_News"
        const val PRIVACY_POLICY_LINK =
            "https://www.privacypolicies.com/live/bc0f3dcd-c684-4f08-aae3-d48ce4945b8d"
    }
}

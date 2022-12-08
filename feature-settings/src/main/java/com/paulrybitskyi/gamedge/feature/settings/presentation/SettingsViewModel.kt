package com.paulrybitskyi.gamedge.feature.settings.presentation

import androidx.lifecycle.viewModelScope
import ca.on.hojat.gamenews.shared.core.Constants
import ca.on.hojat.gamenews.shared.domain.common.DispatcherProvider
import com.paulrybitskyi.gamedge.common.ui.base.BaseViewModel
import ca.on.hojat.gamenews.shared.domain.common.extensions.execute
import com.paulrybitskyi.gamedge.feature.settings.domain.entities.Settings
import com.paulrybitskyi.gamedge.feature.settings.domain.entities.Theme
import com.paulrybitskyi.gamedge.feature.settings.domain.usecases.ObserveSettingsUseCase
import com.paulrybitskyi.gamedge.feature.settings.domain.usecases.SaveSettingsUseCase
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
        dispatchCommand(SettingsCommand.OpenUrl(Constants.SOURCE_CODE_LINK))
    }
}

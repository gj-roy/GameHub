package ca.on.hojat.gamenews.feature_settings.presentation

import app.cash.turbine.test
import ca.on.hojat.gamenews.feature_settings.Constants
import ca.on.hojat.gamenews.feature_settings.DOMAIN_SETTINGS
import ca.on.hojat.gamenews.feature_settings.domain.entities.Settings
import ca.on.hojat.gamenews.feature_settings.domain.entities.Theme
import ca.on.hojat.gamenews.feature_settings.domain.usecases.ObserveSettingsUseCase
import ca.on.hojat.gamenews.feature_settings.domain.usecases.SaveSettingsUseCase
import ca.on.hojat.gamenews.core.domain.common.usecases.execute
import ca.on.hojat.gamenews.shared.testing.domain.MainCoroutineRule
import ca.on.hojat.gamenews.core.common_ui.widgets.FiniteUiState
import com.google.common.truth.Truth.assertThat
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

internal class SettingsViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(StandardTestDispatcher())

    private val saveSettingsUseCase = mockk<SaveSettingsUseCase>(relaxed = true)
    private val observeSettingsUseCase = mockk<ObserveSettingsUseCase>(relaxed = true)

    private val sut by lazy {
        SettingsViewModel(
            saveSettingsUseCase = saveSettingsUseCase,
            observeSettingsUseCase = observeSettingsUseCase,
            uiModelMapper = FakeSettingsUiModelMapper(),
            dispatcherProvider = mainCoroutineRule.dispatcherProvider,
        )
    }

    @Test
    fun `Emits correct ui states when initialization starts`() {
        runTest {
            every { observeSettingsUseCase.execute() } returns flowOf(DOMAIN_SETTINGS)

            sut.uiState.test {
                val loadingState = awaitItem()
                val resultState = awaitItem()

                assertThat(loadingState.finiteUiState).isEqualTo(FiniteUiState.Loading)
                assertThat(resultState.finiteUiState).isEqualTo(FiniteUiState.Success)
                assertThat(resultState.sections).hasSize(FakeSettingsUiModelMapper.SECTION_ITEM_COUNT)
                assertThat(resultState.selectedThemeName).isEqualTo(DOMAIN_SETTINGS.theme.name)
            }
        }
    }

    @Test
    fun `Shows theme picker if theme setting is clicked`() {
        runTest {
            sut.uiState.test {
                sut.onSettingClicked(createSettingUiModel(SettingItem.THEME))

                val stateWithInvisiblePicker = awaitItem()
                val stateWithVisiblePicker = awaitItem()

                assertThat(stateWithInvisiblePicker.isThemePickerVisible).isFalse()
                assertThat(stateWithVisiblePicker.isThemePickerVisible).isTrue()
            }
        }
    }

    @Test
    fun `Hides theme picker when theme gets picked`() {
        runTest {
            sut.onSettingClicked(createSettingUiModel(SettingItem.THEME))

            sut.uiState.test {
                sut.onThemePicked(Theme.LIGHT)

                val stateWithVisiblePicker = awaitItem()
                val stateWithInvisiblePicker = awaitItem()

                assertThat(stateWithVisiblePicker.isThemePickerVisible).isTrue()
                assertThat(stateWithInvisiblePicker.isThemePickerVisible).isFalse()
            }
        }
    }

    @Test
    fun `Updates setting with new theme when theme gets picked`() {
        runTest {
            val defaultSettings = DOMAIN_SETTINGS.copy(theme = Theme.LIGHT)
            val newSettings = defaultSettings.copy(theme = Theme.DARK)

            every { observeSettingsUseCase.execute() } returns flowOf(defaultSettings)

            sut.onThemePicked(newSettings.theme)

            advanceUntilIdle()

            coVerify { saveSettingsUseCase.execute(newSettings) }
        }
    }

    @Test
    fun `Hides theme picker when picker gets dismissed`() {
        runTest {
            sut.onSettingClicked(createSettingUiModel(SettingItem.THEME))

            sut.uiState.test {
                sut.onThemePickerDismissed()

                val stateWithVisiblePicker = awaitItem()
                val stateWithInvisiblePicker = awaitItem()

                assertThat(stateWithVisiblePicker.isThemePickerVisible).isTrue()
                assertThat(stateWithInvisiblePicker.isThemePickerVisible).isFalse()
            }
        }
    }

    @Test
    fun `Opens source code link if source code setting is clicked`() {
        runTest {
            sut.commandFlow.test {
                sut.onSettingClicked(createSettingUiModel(SettingItem.SOURCE_CODE))

                val command = awaitItem()

                assertThat(command).isInstanceOf(SettingsCommand.OpenUrl::class.java)
                assertThat((command as SettingsCommand.OpenUrl).url).isEqualTo(Constants.SOURCE_CODE_LINK)
            }
        }
    }

    private fun createSettingUiModel(setting: SettingItem): SettingsSectionItemUiModel {
        return SettingsSectionItemUiModel(
            id = setting.id,
            title = "title",
            description = "description",
        )
    }

    private class FakeSettingsUiModelMapper : SettingsUiModelMapper {

        companion object {
            const val SECTION_ITEM_COUNT = 3
        }

        override fun mapToUiModels(settings: Settings): List<SettingsSectionUiModel> {
            return buildList {
                repeat(SECTION_ITEM_COUNT) { index ->
                    add(
                        SettingsSectionUiModel(
                            id = index,
                            title = "title$index",
                            items = emptyList(),
                        )
                    )
                }
            }
        }
    }
}

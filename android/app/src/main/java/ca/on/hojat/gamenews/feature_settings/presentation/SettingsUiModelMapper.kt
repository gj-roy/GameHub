package ca.on.hojat.gamenews.feature_settings.presentation

import ca.on.hojat.gamenews.BuildConfig
import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.core.providers.StringProvider
import ca.on.hojat.gamenews.core.providers.VersionNameProvider
import ca.on.hojat.gamenews.feature_settings.domain.entities.Settings
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

internal interface SettingsUiModelMapper {
    fun mapToUiModels(settings: Settings): List<SettingsSectionUiModel>
}

@BindType(installIn = BindType.Component.VIEW_MODEL)
internal class SettingsUiModelMapperImpl @Inject constructor(
    private val stringProvider: StringProvider,
    private val versionNameProvider: VersionNameProvider,
) : SettingsUiModelMapper {

    override fun mapToUiModels(settings: Settings): List<SettingsSectionUiModel> {
        return listOf(
            createGeneralSection(settings),
            createAboutSection(),
        )
    }

    /**
     * The "General" section of the settings page.
     */
    private fun createGeneralSection(settings: Settings): SettingsSectionUiModel {
        return SettingsSectionUiModel(
            id = SettingSection.GENERAL.id,
            title = stringProvider.getString(R.string.settings_section_general_title),
            items = listOf(
                SettingsSectionItemUiModel(
                    id = SettingItem.THEME.id,
                    title = stringProvider.getString(R.string.settings_item_theme_title),
                    description = stringProvider.getString(settings.theme.uiTextRes),
                ),
                SettingsSectionItemUiModel(
                    id = SettingItem.LANGUAGE.id,
                    title = stringProvider.getString(R.string.settings_item_language_title),
                    description = stringProvider.getString(settings.language.uiTextRes)
                )
            )
        )
    }

    /**
     * The "About" section of the settings page.
     */
    private fun createAboutSection(): SettingsSectionUiModel {
        return SettingsSectionUiModel(
            id = SettingSection.ABOUT.id,
            title = stringProvider.getString(R.string.settings_section_about_title),
            items = listOf(
                SettingsSectionItemUiModel(
                    id = SettingItem.SOURCE_CODE.id,
                    title = stringProvider.getString(R.string.settings_item_source_code_title),
                    description = stringProvider.getString(R.string.settings_item_source_code_description),
                ),
                SettingsSectionItemUiModel(
                    id = SettingItem.VERSION.id,
                    title = stringProvider.getString(R.string.settings_item_version_title),
                    description = versionNameProvider.getVersionName() + if (BuildConfig.DEBUG) {
                        " debug"
                    } else {
                        " release"
                    },
                    isClickable = false,
                ),
                SettingsSectionItemUiModel(
                    id = SettingItem.PRIVACY_POLICY.id,
                    title = stringProvider.getString(R.string.settings_item_privacy_policy_title),
                    description = stringProvider.getString(R.string.settings_item_privacy_policy_description),
                )
            )
        )
    }
}

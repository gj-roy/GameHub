package ca.on.hojat.gamehub.feature_settings.data.datastores

import ca.on.hojat.gamehub.feature_settings.domain.DomainLanguage
import ca.on.hojat.gamehub.feature_settings.domain.DomainSettings
import ca.on.hojat.gamehub.feature_settings.domain.DomainTheme
import javax.inject.Inject

internal class ProtoSettingsMapper @Inject constructor() {

    fun mapToProtoSettings(settings: DomainSettings): ProtoSettings {
        return ProtoSettings.newBuilder()
            .setThemeName(settings.theme.name)
            .setLanguageName(settings.language.name)
            .build()
    }

    fun mapToDomainSettings(protoSettings: ProtoSettings): DomainSettings {
        return DomainSettings(
            theme = DomainTheme.valueOf(protoSettings.themeName),
            language = DomainLanguage.valueOf(protoSettings.languageName)
        )
    }
}

package ca.on.hojat.gamenews.feature_settings.data.datastores

import ca.on.hojat.gamenews.feature_settings.domain.DomainSettings
import ca.on.hojat.gamenews.feature_settings.domain.DomainTheme
import javax.inject.Inject

internal class ProtoSettingsMapper @Inject constructor() {

    fun mapToProtoSettings(settings: DomainSettings): ProtoSettings {
        return ProtoSettings.newBuilder()
            .setThemeName(settings.theme.name)
            .build()
    }

    fun mapToDomainSettings(protoSettings: ProtoSettings): DomainSettings {
        return DomainSettings(
            theme = DomainTheme.valueOf(protoSettings.themeName),
        )
    }
}

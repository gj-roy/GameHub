package ca.on.hojat.gamehub.feature_settings.presentation

import ca.on.hojat.gamehub.core.events.Command

internal sealed class SettingsCommand : Command {
    data class OpenUrl(val url: String) : SettingsCommand()
}

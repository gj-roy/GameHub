@file:Suppress("MatchingDeclarationName")

package ca.on.hojat.gamenews.feature_settings.presentation

import ca.on.hojat.gamenews.common_ui.base.events.Command

internal sealed class SettingsCommand : Command {
    data class OpenUrl(val url: String) : SettingsCommand()
}

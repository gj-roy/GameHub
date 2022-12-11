@file:Suppress("MatchingDeclarationName")

package com.paulrybitskyi.gamedge.feature_settings.presentation

import ca.on.hojat.gamenews.shared.ui.base.events.Command

internal sealed class SettingsCommand : Command {
    data class OpenUrl(val url: String) : SettingsCommand()
}

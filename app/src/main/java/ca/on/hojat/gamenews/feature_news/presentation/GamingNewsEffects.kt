@file:Suppress("MatchingDeclarationName")

package ca.on.hojat.gamenews.feature_news.presentation

import ca.on.hojat.gamenews.common_ui.base.events.Command

internal sealed class GamingNewsCommand : Command {
    data class OpenUrl(val url: String) : GamingNewsCommand()
}

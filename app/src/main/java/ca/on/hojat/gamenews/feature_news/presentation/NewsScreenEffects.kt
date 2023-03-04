@file:Suppress("MatchingDeclarationName")

package ca.on.hojat.gamenews.feature_news.presentation

import ca.on.hojat.gamenews.core.events.Command
import ca.on.hojat.gamenews.core.events.Route

internal sealed class NewsScreenCommand : Command {
    data class OpenUrl(val url: String) : NewsScreenCommand()
}

sealed class NewsScreenRoute : Route {
    data class ArticleScreen(
        val imageUrl: String?,
        val title: String,
        val lede: String,
        val publicationDate: String,
        val articleUrl: String,
        val body: String,
    ) : NewsScreenRoute()
}
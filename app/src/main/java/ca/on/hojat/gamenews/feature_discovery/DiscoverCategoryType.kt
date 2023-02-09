package ca.on.hojat.gamenews.feature_discovery

import ca.on.hojat.gamenews.feature_discovery.widgets.DiscoverScreen

/**
 * Any of the different categories in the [DiscoverScreen].
 */
internal enum class DiscoverCategoryType(val id: Int) {
    POPULAR(id = 1),
    RECENTLY_RELEASED(id = 2),
    COMING_SOON(id = 3),
    MOST_ANTICIPATED(id = 4),
}

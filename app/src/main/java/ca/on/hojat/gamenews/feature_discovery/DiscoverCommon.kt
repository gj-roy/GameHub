package ca.on.hojat.gamenews.feature_discovery

import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.feature_discovery.di.DiscoverKey

internal val DiscoverType.titleId: Int
    get() = when (this) {
        DiscoverType.POPULAR -> R.string.games_category_popular
        DiscoverType.RECENTLY_RELEASED -> R.string.games_category_recently_released
        DiscoverType.COMING_SOON -> R.string.games_category_coming_soon
        DiscoverType.MOST_ANTICIPATED -> R.string.games_category_most_anticipated
    }

internal fun DiscoverType.toKeyType(): DiscoverKey.Type {
    return when (this) {
        DiscoverType.POPULAR -> DiscoverKey.Type.POPULAR
        DiscoverType.RECENTLY_RELEASED -> DiscoverKey.Type.RECENTLY_RELEASED
        DiscoverType.COMING_SOON -> DiscoverKey.Type.COMING_SOON
        DiscoverType.MOST_ANTICIPATED -> DiscoverKey.Type.MOST_ANTICIPATED
    }
}

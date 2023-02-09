package ca.on.hojat.gamenews.feature_discovery

import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.feature_discovery.di.DiscoverKey

internal val DiscoverCategoryType.titleId: Int
    get() = when (this) {
        DiscoverCategoryType.POPULAR -> R.string.games_category_popular
        DiscoverCategoryType.RECENTLY_RELEASED -> R.string.games_category_recently_released
        DiscoverCategoryType.COMING_SOON -> R.string.games_category_coming_soon
        DiscoverCategoryType.MOST_ANTICIPATED -> R.string.games_category_most_anticipated
    }

internal fun DiscoverCategoryType.toKeyType(): DiscoverKey.Type {
    return when (this) {
        DiscoverCategoryType.POPULAR -> DiscoverKey.Type.POPULAR
        DiscoverCategoryType.RECENTLY_RELEASED -> DiscoverKey.Type.RECENTLY_RELEASED
        DiscoverCategoryType.COMING_SOON -> DiscoverKey.Type.COMING_SOON
        DiscoverCategoryType.MOST_ANTICIPATED -> DiscoverKey.Type.MOST_ANTICIPATED
    }
}

package ca.on.hojat.gamenews.feature_category

import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.feature_category.di.GamesCategoryKey

internal val CategoryType.titleId: Int
    get() = when (this) {
        CategoryType.POPULAR -> R.string.games_category_popular
        CategoryType.RECENTLY_RELEASED -> R.string.games_category_recently_released
        CategoryType.COMING_SOON -> R.string.games_category_coming_soon
        CategoryType.MOST_ANTICIPATED -> R.string.games_category_most_anticipated
    }

internal fun CategoryType.toKeyType(): GamesCategoryKey.Type {
    return when (this) {
        CategoryType.POPULAR -> GamesCategoryKey.Type.POPULAR
        CategoryType.RECENTLY_RELEASED -> GamesCategoryKey.Type.RECENTLY_RELEASED
        CategoryType.COMING_SOON -> GamesCategoryKey.Type.COMING_SOON
        CategoryType.MOST_ANTICIPATED -> GamesCategoryKey.Type.MOST_ANTICIPATED
    }
}

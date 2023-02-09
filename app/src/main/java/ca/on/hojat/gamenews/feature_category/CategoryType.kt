package ca.on.hojat.gamenews.feature_category

import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.feature_category.di.GamesCategoryKey

internal enum class CategoryType {
    POPULAR,
    RECENTLY_RELEASED,
    COMING_SOON,
    MOST_ANTICIPATED;

    val titleId: Int
        get() = when (this) {
            POPULAR -> R.string.games_category_popular
            RECENTLY_RELEASED -> R.string.games_category_recently_released
            COMING_SOON -> R.string.games_category_coming_soon
            MOST_ANTICIPATED -> R.string.games_category_most_anticipated
        }

    fun toKeyType(): GamesCategoryKey.Type {
        return when (this) {
            POPULAR -> GamesCategoryKey.Type.POPULAR
            RECENTLY_RELEASED -> GamesCategoryKey.Type.RECENTLY_RELEASED
            COMING_SOON -> GamesCategoryKey.Type.COMING_SOON
            MOST_ANTICIPATED -> GamesCategoryKey.Type.MOST_ANTICIPATED
        }
    }
}

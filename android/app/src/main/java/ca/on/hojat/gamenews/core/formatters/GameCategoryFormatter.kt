package ca.on.hojat.gamenews.core.formatters

import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.core.domain.entities.Category
import ca.on.hojat.gamenews.core.providers.StringProvider
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

interface GameCategoryFormatter {
    fun formatCategory(category: Category): String
}

@BindType
internal class GameCategoryFormatterImpl @Inject constructor(
    private val stringProvider: StringProvider
) : GameCategoryFormatter {

    override fun formatCategory(category: Category): String {
        return stringProvider.getString(
            when (category) {
                Category.UNKNOWN -> R.string.not_available_abbr
                Category.MAIN_GAME -> R.string.game_category_main
                Category.BUNDLE -> R.string.game_category_bundle
                Category.MOD -> R.string.game_category_mod
                Category.EPISODE -> R.string.game_category_episode
                Category.SEASON -> R.string.game_category_season

                Category.DLC, Category.EXPANSION, Category.STANDALONE_EXPANSION -> R.string.game_category_dlc
            }
        )
    }
}

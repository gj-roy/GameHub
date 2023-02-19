package ca.on.hojat.gamenews.feature_settings.domain.entities

import ca.on.hojat.gamenews.R

enum class Theme {
    LIGHT,
    DARK,
    SYSTEM,
}

internal val Theme.uiTextRes: Int
    get() = when (this) {
        Theme.LIGHT -> R.string.settings_item_theme_option_light
        Theme.DARK -> R.string.settings_item_theme_option_dark
        Theme.SYSTEM -> R.string.settings_item_theme_option_system_default
    }

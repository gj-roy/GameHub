package ca.on.hojat.gamenews.feature_settings.domain.entities

import ca.on.hojat.gamenews.R

enum class Language(val uiTextRes: Int) {
    ENGLISH(R.string.settings_item_language_option_english),
    PERSIAN(R.string.settings_item_language_option_persian),
    Russian(R.string.settings_item_language_option_russian)
}
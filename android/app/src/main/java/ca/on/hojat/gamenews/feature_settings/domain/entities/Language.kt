package ca.on.hojat.gamenews.feature_settings.domain.entities

import ca.on.hojat.gamenews.R

enum class Language(val uiTextRes: Int, val locale: String) {
    ENGLISH(R.string.settings_item_language_option_english, "en"),
    PERSIAN(R.string.settings_item_language_option_persian, "fa"),
    RUSSIAN(R.string.settings_item_language_option_russian, "ru")
}
package ca.on.hojat.gamenews.feature_settings.domain.entities

data class Settings(
    val theme: Theme,
) {
    companion object {
        val DEFAULT = Settings(
            theme = Theme.SYSTEM,
        )
    }
}

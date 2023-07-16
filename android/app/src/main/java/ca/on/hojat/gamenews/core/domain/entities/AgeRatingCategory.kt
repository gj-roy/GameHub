package ca.on.hojat.gamenews.core.domain.entities

enum class AgeRatingCategory(val title: String) {
    UNKNOWN(title = ""),
    ESRB(title = "ESRB"),
    PEGI(title = "PEGI"),
}

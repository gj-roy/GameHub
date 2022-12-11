package ca.on.hojat.gamenews.shared.domain.games.entities

data class ReleaseDate(
    val date: Long?,
    val year: Int?,
    val category: ReleaseDateCategory,
)

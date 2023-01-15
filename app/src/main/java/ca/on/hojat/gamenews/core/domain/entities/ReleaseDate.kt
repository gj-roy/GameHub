package ca.on.hojat.gamenews.core.domain.entities

data class ReleaseDate(
    val date: Long?,
    val year: Int?,
    val category: ReleaseDateCategory,
)

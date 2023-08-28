package ca.on.hojat.gamehub.core.domain.entities

data class ReleaseDate(
    val date: Long?,
    val year: Int?,
    val category: ReleaseDateCategory,
)

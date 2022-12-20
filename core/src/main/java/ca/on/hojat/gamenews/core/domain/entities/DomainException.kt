package ca.on.hojat.gamenews.core.domain.entities

class DomainException(
    val error: Error,
    cause: Throwable? = null
) : Exception(error.toString(), cause)

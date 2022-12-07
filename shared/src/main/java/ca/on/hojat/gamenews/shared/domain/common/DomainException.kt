package ca.on.hojat.gamenews.shared.domain.common

import ca.on.hojat.gamenews.shared.domain.common.entities.Error

class DomainException(
    val error: Error,
    cause: Throwable? = null
) : Exception(error.toString(), cause)

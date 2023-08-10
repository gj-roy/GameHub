package ca.on.hojat.gamehub.core.domain

import com.github.michaelbull.result.Result
import ca.on.hojat.gamehub.core.domain.entities.Error

typealias DomainResult<T> = Result<T, Error>

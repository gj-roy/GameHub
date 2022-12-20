package ca.on.hojat.gamenews.core.domain

import com.github.michaelbull.result.Result
import ca.on.hojat.gamenews.core.domain.entities.Error

typealias DomainResult<T> = Result<T, Error>

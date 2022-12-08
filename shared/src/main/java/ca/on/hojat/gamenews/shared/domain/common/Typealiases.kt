package ca.on.hojat.gamenews.shared.domain.common

import com.github.michaelbull.result.Result
import ca.on.hojat.gamenews.shared.domain.common.entities.Error

typealias DomainResult<T> = Result<T, Error>

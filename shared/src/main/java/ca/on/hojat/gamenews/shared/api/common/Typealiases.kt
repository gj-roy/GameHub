package ca.on.hojat.gamenews.shared.api.common

import com.github.michaelbull.result.Result
import ca.on.hojat.gamenews.core.data.api.common.Error

typealias ApiResult<T> = Result<T, Error>

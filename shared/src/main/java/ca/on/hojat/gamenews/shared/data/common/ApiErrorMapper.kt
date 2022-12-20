package ca.on.hojat.gamenews.shared.data.common

import ca.on.hojat.gamenews.core.domain.entities.Error
import ca.on.hojat.gamenews.shared.api.common.httpErrorMessage
import ca.on.hojat.gamenews.shared.api.common.isHttpError
import ca.on.hojat.gamenews.shared.api.common.isNetworkError
import ca.on.hojat.gamenews.shared.api.common.isServerError
import ca.on.hojat.gamenews.shared.api.common.isUnknownError
import ca.on.hojat.gamenews.shared.api.common.networkErrorMessage
import ca.on.hojat.gamenews.shared.api.common.unknownErrorMessage
import javax.inject.Inject
import kotlin.IllegalStateException
import kotlin.with

import ca.on.hojat.gamenews.shared.api.common.Error as ApiError

class ApiErrorMapper @Inject constructor() {

    fun mapToDomainError(apiError: ApiError): Error = with(apiError) {
        return when {
            isServerError -> Error.ApiError.ServiceUnavailable
            isHttpError -> Error.ApiError.ClientError(httpErrorMessage)
            isNetworkError -> Error.ApiError.NetworkError(networkErrorMessage)
            isUnknownError -> Error.ApiError.Unknown(unknownErrorMessage)

            else -> throw IllegalStateException("Could not map the api error $this to a data error.")
        }
    }
}

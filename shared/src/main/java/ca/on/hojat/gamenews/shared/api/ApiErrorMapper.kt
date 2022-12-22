package ca.on.hojat.gamenews.shared.api

import ca.on.hojat.gamenews.core.domain.entities.Error
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

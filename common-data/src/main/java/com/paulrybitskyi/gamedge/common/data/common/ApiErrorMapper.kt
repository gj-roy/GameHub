/*
 * Copyright 2022 Paul Rybitskyi, paul.rybitskyi.work@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.paulrybitskyi.gamedge.common.data.common

import ca.on.hojat.gamenews.api.common.httpErrorMessage
import ca.on.hojat.gamenews.api.common.isHttpError
import ca.on.hojat.gamenews.api.common.isNetworkError
import ca.on.hojat.gamenews.api.common.isServerError
import ca.on.hojat.gamenews.api.common.isUnknownError
import ca.on.hojat.gamenews.api.common.networkErrorMessage
import ca.on.hojat.gamenews.api.common.unknownErrorMessage
import com.paulrybitskyi.gamedge.common.domain.common.entities.Error
import javax.inject.Inject
import kotlin.IllegalStateException
import kotlin.with
import ca.on.hojat.gamenews.api.common.Error as ApiError

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

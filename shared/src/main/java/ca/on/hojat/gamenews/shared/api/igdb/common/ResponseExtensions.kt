package ca.on.hojat.gamenews.shared.api.igdb.common

import okhttp3.Response

internal val Response.responseCount: Int
    get() {
        var result = 1
        var response: Response? = priorResponse

        while (response != null) {
            result++

            response = response.priorResponse
        }

        return result
    }

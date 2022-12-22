package ca.on.hojat.gamenews.core.data.api.common

interface ErrorMessageExtractor {
    fun extract(responseBody: String): String
}

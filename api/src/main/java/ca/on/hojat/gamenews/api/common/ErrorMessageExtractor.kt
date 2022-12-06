package ca.on.hojat.gamenews.api.common

interface ErrorMessageExtractor {
    fun extract(responseBody: String): String
}

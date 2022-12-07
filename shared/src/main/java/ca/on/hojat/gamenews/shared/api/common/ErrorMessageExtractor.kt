package ca.on.hojat.gamenews.shared.api.common

interface ErrorMessageExtractor {
    fun extract(responseBody: String): String
}

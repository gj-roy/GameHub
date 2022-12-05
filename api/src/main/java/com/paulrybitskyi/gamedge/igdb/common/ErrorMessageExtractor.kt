package com.paulrybitskyi.gamedge.igdb.common

interface ErrorMessageExtractor {
    fun extract(responseBody: String): String
}

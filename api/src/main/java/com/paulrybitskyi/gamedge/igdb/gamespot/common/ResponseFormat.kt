package com.paulrybitskyi.gamedge.igdb.gamespot.common

internal enum class ResponseFormat(val rawFormat: String) {
    XML("xml"),
    JSON("json"),
    JSONP("jsonp")
}

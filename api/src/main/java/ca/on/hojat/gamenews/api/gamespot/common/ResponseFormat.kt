package ca.on.hojat.gamenews.api.gamespot.common

internal enum class ResponseFormat(val rawFormat: String) {
    XML("xml"),
    JSON("json"),
    JSONP("jsonp")
}

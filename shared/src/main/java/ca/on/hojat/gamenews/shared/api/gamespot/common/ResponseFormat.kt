package ca.on.hojat.gamenews.shared.api.gamespot.common

internal enum class ResponseFormat(val rawFormat: String) {
    XML("xml"),
    JSON("json"),
    JSONP("jsonp")
}

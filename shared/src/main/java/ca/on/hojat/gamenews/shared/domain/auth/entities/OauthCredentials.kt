package ca.on.hojat.gamenews.shared.domain.auth.entities

data class OauthCredentials(
    val accessToken: String,
    val tokenType: String,
    val tokenTtl: Long
)

package ca.on.hojat.gamehub.core.data

import ca.on.hojat.gamehub.core.domain.auth.entities.OauthCredentials
import ca.on.hojat.gamehub.core.domain.entities.Company

internal val DOMAIN_OAUTH_CREDENTIALS = OauthCredentials(
    accessToken = "access_token",
    tokenType = "token_type",
    tokenTtl = 5000L,
)

internal val DOMAIN_COMPANY = Company(
    id = 1,
    name = "name",
    websiteUrl = "website_url",
    logo = null,
    developedGames = listOf(1, 2, 3),
)

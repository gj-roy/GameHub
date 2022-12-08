package com.paulrybitskyi.gamedge.common.data.auth.datastores.igdb

import ca.on.hojat.gamenews.shared.api.igdb.auth.entities.ApiOauthCredentials
import ca.on.hojat.gamenews.shared.domain.auth.entities.OauthCredentials
import javax.inject.Inject

internal class IgdbAuthMapper @Inject constructor() {

    fun mapToApiOauthCredentials(oauthCredentials: OauthCredentials): ApiOauthCredentials {
        return ApiOauthCredentials(
            accessToken = oauthCredentials.accessToken,
            tokenType = oauthCredentials.tokenType,
            tokenTtl = oauthCredentials.tokenTtl
        )
    }

    fun mapToDomainOauthCredentials(apiOauthCredentials: ApiOauthCredentials): OauthCredentials {
        return OauthCredentials(
            accessToken = apiOauthCredentials.accessToken,
            tokenType = apiOauthCredentials.tokenType,
            tokenTtl = apiOauthCredentials.tokenTtl
        )
    }
}

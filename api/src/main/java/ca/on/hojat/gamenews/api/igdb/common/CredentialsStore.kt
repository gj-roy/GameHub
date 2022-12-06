package ca.on.hojat.gamenews.api.igdb.common

import ca.on.hojat.gamenews.api.igdb.auth.entities.ApiOauthCredentials

interface CredentialsStore {
    suspend fun saveOauthCredentials(oauthCredentials: ApiOauthCredentials)
    suspend fun getLocalOauthCredentials(): ApiOauthCredentials?
    suspend fun getRemoteOauthCredentials(): ApiOauthCredentials?
}

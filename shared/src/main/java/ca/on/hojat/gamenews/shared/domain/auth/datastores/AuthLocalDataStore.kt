package ca.on.hojat.gamenews.shared.domain.auth.datastores

import ca.on.hojat.gamenews.shared.domain.auth.entities.OauthCredentials

interface AuthLocalDataStore {
    suspend fun saveOauthCredentials(oauthCredentials: OauthCredentials)
    suspend fun getOauthCredentials(): OauthCredentials?
}

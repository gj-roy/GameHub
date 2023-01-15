package ca.on.hojat.gamenews.data.api.utils

import ca.on.hojat.gamenews.core.data.api.igdb.auth.entities.ApiOauthCredentials
import ca.on.hojat.gamenews.core.data.api.igdb.common.CredentialsStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FakeCredentialsStore @Inject constructor() : CredentialsStore {

    private var oauthCredentials: ApiOauthCredentials? = null

    override suspend fun saveOauthCredentials(oauthCredentials: ApiOauthCredentials) {
        this.oauthCredentials = oauthCredentials
    }

    override suspend fun getLocalOauthCredentials(): ApiOauthCredentials? {
        return oauthCredentials
    }

    override suspend fun getRemoteOauthCredentials(): ApiOauthCredentials? {
        return oauthCredentials
    }
}

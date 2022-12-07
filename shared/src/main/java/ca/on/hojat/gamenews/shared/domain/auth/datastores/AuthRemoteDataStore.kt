package ca.on.hojat.gamenews.shared.domain.auth.datastores

import ca.on.hojat.gamenews.shared.domain.auth.entities.OauthCredentials
import com.paulrybitskyi.gamedge.common.domain.common.DomainResult

interface AuthRemoteDataStore {
    suspend fun getOauthCredentials(): DomainResult<OauthCredentials>
}

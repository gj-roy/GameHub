package ca.on.hojat.gamenews.shared.api.igdb.auth

import ca.on.hojat.gamenews.core.data.api.common.ApiResult
import ca.on.hojat.gamenews.shared.api.igdb.auth.entities.ApiOauthCredentials
import retrofit2.http.POST
import retrofit2.http.Query

internal interface AuthService {

    @POST("oauth2/token")
    suspend fun getOauthCredentials(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("grant_type") grantType: String
    ): ApiResult<ApiOauthCredentials>
}

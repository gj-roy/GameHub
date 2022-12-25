package ca.on.hojat.gamenews.shared.data.auth.datastores.igdb

import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.core.domain.auth.datastores.AuthRemoteDataStore
import ca.on.hojat.gamenews.core.domain.auth.entities.OauthCredentials
import ca.on.hojat.gamenews.core.data.api.ApiErrorMapper
import ca.on.hojat.gamenews.core.data.api.igdb.auth.AuthEndpoint
import com.github.michaelbull.result.mapEither
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@BindType
internal class AuthIgdbDataStore @Inject constructor(
    private val authEndpoint: AuthEndpoint,
    private val igdbAuthMapper: IgdbAuthMapper,
    private val apiErrorMapper: ApiErrorMapper,
) : AuthRemoteDataStore {

    override suspend fun getOauthCredentials(): DomainResult<OauthCredentials> {
        return authEndpoint
            .getOauthCredentials()
            .mapEither(
                igdbAuthMapper::mapToDomainOauthCredentials,
                apiErrorMapper::mapToDomainError
            )
    }
}

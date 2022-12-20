package ca.on.hojat.gamenews.shared.data.auth.datastores.igdb

import ca.on.hojat.gamenews.shared.api.igdb.auth.AuthEndpoint
import ca.on.hojat.gamenews.shared.domain.auth.datastores.AuthRemoteDataStore
import ca.on.hojat.gamenews.shared.domain.auth.entities.OauthCredentials
import ca.on.hojat.gamenews.core.domain.DomainResult
import com.github.michaelbull.result.mapEither
import ca.on.hojat.gamenews.shared.data.common.ApiErrorMapper
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

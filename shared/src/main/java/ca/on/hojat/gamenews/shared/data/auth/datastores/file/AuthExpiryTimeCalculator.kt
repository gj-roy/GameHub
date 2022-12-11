package ca.on.hojat.gamenews.shared.data.auth.datastores.file

import ca.on.hojat.gamenews.shared.core.providers.TimestampProvider
import ca.on.hojat.gamenews.shared.domain.auth.entities.OauthCredentials
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal val AUTH_TOKEN_TTL_DEDUCTION = TimeUnit.DAYS.toMillis(@Suppress("MagicNumber") 7)

internal class AuthExpiryTimeCalculator @Inject constructor(
    private val timestampProvider: TimestampProvider
) {

    fun calculateExpiryTime(oauthCredentials: OauthCredentials): Long {
        val currentTimestamp = timestampProvider.getUnixTimestamp()
        val tokenTtlInMillis = TimeUnit.SECONDS.toMillis(oauthCredentials.tokenTtl)

        return (currentTimestamp + tokenTtlInMillis - AUTH_TOKEN_TTL_DEDUCTION)
    }
}

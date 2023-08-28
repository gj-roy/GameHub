package ca.on.hojat.gamehub.core.domain.games.common.throttling

import javax.inject.Inject

class GamesRefreshingThrottlerTools @Inject constructor(
    val throttler: GamesRefreshingThrottler,
    val keyProvider: GamesRefreshingThrottlerKeyProvider,
)

package ca.on.hojat.gamenews.core.domain.games.common.throttling

import javax.inject.Inject

class GamesRefreshingThrottlerTools @Inject constructor(
    val throttler: GamesRefreshingThrottler,
    val keyProvider: GamesRefreshingThrottlerKeyProvider,
)

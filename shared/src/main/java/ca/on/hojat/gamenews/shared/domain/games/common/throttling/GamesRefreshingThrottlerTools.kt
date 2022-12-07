package ca.on.hojat.gamenews.shared.domain.games.common.throttling

import ca.on.hojat.gamenews.shared.domain.games.common.throttling.GamesRefreshingThrottler
import ca.on.hojat.gamenews.shared.domain.games.common.throttling.GamesRefreshingThrottlerKeyProvider
import javax.inject.Inject

class GamesRefreshingThrottlerTools @Inject constructor(
    val throttler: GamesRefreshingThrottler,
    val keyProvider: GamesRefreshingThrottlerKeyProvider,
)

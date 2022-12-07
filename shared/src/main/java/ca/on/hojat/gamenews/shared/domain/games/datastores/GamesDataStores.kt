package ca.on.hojat.gamenews.shared.domain.games.datastores

import javax.inject.Inject

class GamesDataStores @Inject constructor(
    val local: GamesLocalDataStore,
    val remote: GamesRemoteDataStore
)

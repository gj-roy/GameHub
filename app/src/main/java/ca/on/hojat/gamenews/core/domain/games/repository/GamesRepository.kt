package ca.on.hojat.gamenews.core.domain.games.repository

import javax.inject.Inject

class GamesRepository @Inject constructor(
    val local: GamesLocalDataSource,
    val remote: GamesRemoteDataSource
)

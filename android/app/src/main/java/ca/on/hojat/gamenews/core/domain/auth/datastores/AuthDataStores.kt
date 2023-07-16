package ca.on.hojat.gamenews.core.domain.auth.datastores

import javax.inject.Inject

class AuthDataStores @Inject constructor(
    val local: AuthLocalDataStore,
    val remote: AuthRemoteDataStore
)

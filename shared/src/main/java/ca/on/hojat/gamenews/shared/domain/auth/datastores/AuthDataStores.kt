package ca.on.hojat.gamenews.shared.domain.auth.datastores

import javax.inject.Inject

class AuthDataStores @Inject constructor(
    val local: AuthLocalDataStore,
    val remote: AuthRemoteDataStore
)

package ca.on.hojat.gamehub.core.domain.games.di

import ca.on.hojat.gamehub.core.domain.games.common.throttling.GamesRefreshingThrottlerKeyProvider
import ca.on.hojat.gamehub.core.domain.games.common.throttling.GamesRefreshingThrottlerKeyProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface CoreModule {

    @Binds
    fun bindGamesRefreshingThrottlerKeyProvider(
        binding: GamesRefreshingThrottlerKeyProviderImpl
    ): GamesRefreshingThrottlerKeyProvider
}

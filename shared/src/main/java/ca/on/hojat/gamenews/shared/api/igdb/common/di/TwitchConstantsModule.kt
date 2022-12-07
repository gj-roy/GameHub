package ca.on.hojat.gamenews.shared.api.igdb.common.di

import ca.on.hojat.gamenews.shared.api.igdb.common.ProdTwitchConstantsProvider
import ca.on.hojat.gamenews.shared.api.igdb.common.TwitchConstantsProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface TwitchConstantsModule {

    @Binds
    fun bindTwitchConstantsProvider(binding: ProdTwitchConstantsProvider): TwitchConstantsProvider
}

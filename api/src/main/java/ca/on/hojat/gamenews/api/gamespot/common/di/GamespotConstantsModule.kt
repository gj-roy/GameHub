package ca.on.hojat.gamenews.api.gamespot.common.di

import ca.on.hojat.gamenews.api.gamespot.common.GamespotConstantsProvider
import ca.on.hojat.gamenews.api.gamespot.common.ProdGamespotConstantsProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface GamespotConstantsModule {

    @Binds
    fun bindGamespotConstantsProvider(binding: ProdGamespotConstantsProvider): GamespotConstantsProvider
}

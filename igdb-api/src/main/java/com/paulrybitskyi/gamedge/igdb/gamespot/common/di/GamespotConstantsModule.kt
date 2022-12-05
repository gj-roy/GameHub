package com.paulrybitskyi.gamedge.igdb.gamespot.common.di

import com.paulrybitskyi.gamedge.igdb.gamespot.common.GamespotConstantsProvider
import com.paulrybitskyi.gamedge.igdb.gamespot.common.ProdGamespotConstantsProvider
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

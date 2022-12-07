package ca.on.hojat.gamenews.shared.api.igdb.common.di

import ca.on.hojat.gamenews.shared.api.igdb.common.IgdbConstantsProvider
import ca.on.hojat.gamenews.shared.api.igdb.common.ProdIgdbConstantsProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface IgdbConstantsModule {

    @Binds
    fun bindIgdbConstantsProvider(binding: ProdIgdbConstantsProvider): IgdbConstantsProvider
}

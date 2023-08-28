package ca.on.hojat.gamehub.core.domain.common.di

import ca.on.hojat.gamehub.core.domain.common.DispatcherProvider
import ca.on.hojat.gamehub.core.domain.common.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface CoreModule {

    @Binds
    fun bindDispatcherProvider(binding: DispatcherProviderImpl): DispatcherProvider
}

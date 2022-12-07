package ca.on.hojat.gamenews.shared.api.di

import ca.on.hojat.gamenews.shared.api.utils.FakeTwitchConstantsProvider
import ca.on.hojat.gamenews.shared.api.igdb.common.TwitchConstantsProvider
import ca.on.hojat.gamenews.shared.api.igdb.common.di.TwitchConstantsModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [TwitchConstantsModule::class]
)
internal interface FakeTwitchConstantsModule {

    @Binds
    fun bindAuthConstantsProvider(binding: FakeTwitchConstantsProvider): TwitchConstantsProvider
}

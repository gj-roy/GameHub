package ca.on.hojat.gamenews.shared.api.gamespot.di

import ca.on.hojat.gamenews.shared.api.gamespot.utils.FakeGamespotConstantsProvider
import ca.on.hojat.gamenews.shared.api.gamespot.common.GamespotConstantsProvider
import ca.on.hojat.gamenews.shared.api.gamespot.common.di.GamespotConstantsModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [GamespotConstantsModule::class]
)
internal interface FakeGamespotConstantsModule {

    @Binds
    fun bindGamespotConstantsProvider(binding: FakeGamespotConstantsProvider): GamespotConstantsProvider
}

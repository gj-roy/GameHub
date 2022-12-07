package ca.on.hojat.gamenews.shared.api.di

import ca.on.hojat.gamenews.shared.api.utils.FakeIgdbConstantsProvider
import ca.on.hojat.gamenews.shared.api.igdb.common.IgdbConstantsProvider
import ca.on.hojat.gamenews.shared.api.igdb.common.di.IgdbConstantsModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [IgdbConstantsModule::class]
)
internal interface FakeIgdbConstantsModule {

    @Binds
    fun bindIgdbConstantsProvider(binding: FakeIgdbConstantsProvider): IgdbConstantsProvider
}

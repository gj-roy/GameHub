package ca.on.hojat.gamenews.api.di

import com.paulrybitskyi.gamedge.igdb.api.common.IgdbConstantsProvider
import com.paulrybitskyi.gamedge.igdb.api.common.di.IgdbConstantsModule
import ca.on.hojat.gamenews.api.utils.FakeIgdbConstantsProvider
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

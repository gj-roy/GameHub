package ca.on.hojat.gamenews.api.di

import com.paulrybitskyi.gamedge.igdb.api.common.TwitchConstantsProvider
import com.paulrybitskyi.gamedge.igdb.api.common.di.TwitchConstantsModule
import ca.on.hojat.gamenews.api.utils.FakeTwitchConstantsProvider
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

package ca.on.hojat.gamenews.shared.api.di

import ca.on.hojat.gamenews.shared.api.utils.FakeCredentialsStore
import ca.on.hojat.gamenews.shared.api.igdb.common.CredentialsStore
import ca.on.hojat.gamenews.shared.testing.di.MocksModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [MocksModule::class])
@InstallIn(SingletonComponent::class)
internal interface TestModule {

    @Binds
    fun bind(binding: FakeCredentialsStore): CredentialsStore
}

package ca.on.hojat.gamenews.shared.api.gamespot.di

import ca.on.hojat.gamenews.shared.testing.di.MocksModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [MocksModule::class])
@InstallIn(SingletonComponent::class)
internal object TestModule

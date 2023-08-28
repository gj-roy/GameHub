package ca.on.hojat.gamehub.presentation.di

import ca.on.hojat.gamehub.presentation.TransitionAnimations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonUiModule {

    @Provides
    @Singleton
    @TransitionAnimationDuration
    fun provideTransitionAnimationDuration(): Long {
        return TransitionAnimations.DefaultAnimationDuration.toLong()
    }
}

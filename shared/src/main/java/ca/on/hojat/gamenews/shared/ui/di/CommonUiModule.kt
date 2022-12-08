package ca.on.hojat.gamenews.shared.ui.di

import ca.on.hojat.gamenews.shared.ui.TransitionAnimations
import ca.on.hojat.gamenews.shared.ui.di.qualifiers.TransitionAnimationDuration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CommonUiModule {

    @Provides
    @Singleton
    @TransitionAnimationDuration
    fun provideTransitionAnimationDuration(): Long {
        return TransitionAnimations.DefaultAnimationDuration.toLong()
    }
}

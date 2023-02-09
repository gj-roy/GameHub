package ca.on.hojat.gamenews.feature_discovery.di

import ca.on.hojat.gamenews.core.domain.games.ObservableGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.RefreshableGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.usecases.ObserveComingSoonGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.usecases.ObserveMostAnticipatedGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.usecases.ObservePopularGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.usecases.ObserveRecentlyReleasedGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.usecases.RefreshComingSoonGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.usecases.RefreshMostAnticipatedGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.usecases.RefreshPopularGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.usecases.RefreshRecentlyReleasedGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoMap

/**
 * This is the main DI module for discover feature of the app.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
internal object DiscoverModule {

    @Provides
    @IntoMap
    @DiscoverKey(DiscoverKey.Type.POPULAR)
    fun providePopularGamesObserverUseCase(
        observePopularGamesUseCase: ObservePopularGamesUseCase
    ): ObservableGamesUseCase {
        return observePopularGamesUseCase
    }

    @Provides
    @IntoMap
    @DiscoverKey(DiscoverKey.Type.POPULAR)
    fun providePopularGamesRefresherUseCase(
        refreshPopularGamesUseCase: RefreshPopularGamesUseCase
    ): RefreshableGamesUseCase {
        return refreshPopularGamesUseCase
    }

    @Provides
    @IntoMap
    @DiscoverKey(DiscoverKey.Type.RECENTLY_RELEASED)
    fun provideRecentlyReleasedGamesObserverUseCase(
        observeRecentlyReleasedGamesUseCase: ObserveRecentlyReleasedGamesUseCase
    ): ObservableGamesUseCase {
        return observeRecentlyReleasedGamesUseCase
    }

    @Provides
    @IntoMap
    @DiscoverKey(DiscoverKey.Type.RECENTLY_RELEASED)
    fun provideRecentlyReleasedGamesRefresherUseCase(
        refreshRecentlyReleasedGamesUseCase: RefreshRecentlyReleasedGamesUseCase
    ): RefreshableGamesUseCase {
        return refreshRecentlyReleasedGamesUseCase
    }

    @Provides
    @IntoMap
    @DiscoverKey(DiscoverKey.Type.COMING_SOON)
    fun provideComingSoonGamesObserverUseCase(
        observeComingSoonGamesUseCase: ObserveComingSoonGamesUseCase
    ): ObservableGamesUseCase {
        return observeComingSoonGamesUseCase
    }

    @Provides
    @IntoMap
    @DiscoverKey(DiscoverKey.Type.COMING_SOON)
    fun provideComingSoonGamesRefresherUseCase(
        refreshComingSoonGamesUseCase: RefreshComingSoonGamesUseCase
    ): RefreshableGamesUseCase {
        return refreshComingSoonGamesUseCase
    }

    @Provides
    @IntoMap
    @DiscoverKey(DiscoverKey.Type.MOST_ANTICIPATED)
    fun provideMostAnticipatedGamesObserverUseCase(
        observeMostAnticipatedGamesUseCase: ObserveMostAnticipatedGamesUseCase
    ): ObservableGamesUseCase {
        return observeMostAnticipatedGamesUseCase
    }

    @Provides
    @IntoMap
    @DiscoverKey(DiscoverKey.Type.MOST_ANTICIPATED)
    fun provideMostAnticipatedGamesRefresherUseCase(
        refreshMostAnticipatedGamesUseCase: RefreshMostAnticipatedGamesUseCase
    ): RefreshableGamesUseCase {
        return refreshMostAnticipatedGamesUseCase
    }
}

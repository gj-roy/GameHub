package ca.on.hojat.gamenews.feature_category.di

import ca.on.hojat.gamenews.shared.domain.games.ObservableGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.RefreshableGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.ObserveComingSoonGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.ObserveMostAnticipatedGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.ObservePopularGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.ObserveRecentlyReleasedGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.RefreshComingSoonGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.RefreshMostAnticipatedGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.RefreshPopularGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.RefreshRecentlyReleasedGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityRetainedComponent::class)
internal object GamesCategoryModule {

    @Provides
    @IntoMap
    @GamesCategoryKey(GamesCategoryKey.Type.POPULAR)
    fun providePopularGamesObserverUseCase(
        observePopularGamesUseCase: ObservePopularGamesUseCase
    ): ObservableGamesUseCase {
        return observePopularGamesUseCase
    }

    @Provides
    @IntoMap
    @GamesCategoryKey(GamesCategoryKey.Type.POPULAR)
    fun providePopularGamesRefresherUseCase(
        refreshPopularGamesUseCase: RefreshPopularGamesUseCase
    ): RefreshableGamesUseCase {
        return refreshPopularGamesUseCase
    }

    @Provides
    @IntoMap
    @GamesCategoryKey(GamesCategoryKey.Type.RECENTLY_RELEASED)
    fun provideRecentlyReleasedGamesObserverUseCase(
        observeRecentlyReleasedGamesUseCase: ObserveRecentlyReleasedGamesUseCase
    ): ObservableGamesUseCase {
        return observeRecentlyReleasedGamesUseCase
    }

    @Provides
    @IntoMap
    @GamesCategoryKey(GamesCategoryKey.Type.RECENTLY_RELEASED)
    fun provideRecentlyReleasedGamesRefresherUseCase(
        refreshRecentlyReleasedGamesUseCase: RefreshRecentlyReleasedGamesUseCase
    ): RefreshableGamesUseCase {
        return refreshRecentlyReleasedGamesUseCase
    }

    @Provides
    @IntoMap
    @GamesCategoryKey(GamesCategoryKey.Type.COMING_SOON)
    fun provideComingSoonGamesObserverUseCase(
        observeComingSoonGamesUseCase: ObserveComingSoonGamesUseCase
    ): ObservableGamesUseCase {
        return observeComingSoonGamesUseCase
    }

    @Provides
    @IntoMap
    @GamesCategoryKey(GamesCategoryKey.Type.COMING_SOON)
    fun provideComingSoonGamesRefresherUseCase(
        refreshComingSoonGamesUseCase: RefreshComingSoonGamesUseCase
    ): RefreshableGamesUseCase {
        return refreshComingSoonGamesUseCase
    }

    @Provides
    @IntoMap
    @GamesCategoryKey(GamesCategoryKey.Type.MOST_ANTICIPATED)
    fun provideMostAnticipatedGamesObserverUseCase(
        observeMostAnticipatedGamesUseCase: ObserveMostAnticipatedGamesUseCase
    ): ObservableGamesUseCase {
        return observeMostAnticipatedGamesUseCase
    }

    @Provides
    @IntoMap
    @GamesCategoryKey(GamesCategoryKey.Type.MOST_ANTICIPATED)
    fun provideMostAnticipatedGamesRefresherUseCase(
        refreshMostAnticipatedGamesUseCase: RefreshMostAnticipatedGamesUseCase
    ): RefreshableGamesUseCase {
        return refreshMostAnticipatedGamesUseCase
    }
}

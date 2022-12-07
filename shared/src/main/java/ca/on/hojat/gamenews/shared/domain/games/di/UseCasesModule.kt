package ca.on.hojat.gamenews.shared.domain.games.di

import ca.on.hojat.gamenews.shared.domain.games.usecases.ObserveComingSoonGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.ObserveComingSoonGamesUseCaseImpl
import ca.on.hojat.gamenews.shared.domain.games.usecases.ObserveMostAnticipatedGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.ObserveMostAnticipatedGamesUseCaseImpl
import ca.on.hojat.gamenews.shared.domain.games.usecases.ObservePopularGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.ObservePopularGamesUseCaseImpl
import ca.on.hojat.gamenews.shared.domain.games.usecases.ObserveRecentlyReleasedGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.ObserveRecentlyReleasedGamesUseCaseImpl
import ca.on.hojat.gamenews.shared.domain.games.usecases.RefreshComingSoonGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.RefreshComingSoonGamesUseCaseImpl
import ca.on.hojat.gamenews.shared.domain.games.usecases.RefreshMostAnticipatedGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.RefreshMostAnticipatedGamesUseCaseImpl
import ca.on.hojat.gamenews.shared.domain.games.usecases.RefreshPopularGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.RefreshPopularGamesUseCaseImpl
import ca.on.hojat.gamenews.shared.domain.games.usecases.RefreshRecentlyReleasedGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.usecases.RefreshRecentlyReleasedGamesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCasesModule {

    @Binds
    fun bindObserveComingSoonGamesUseCase(
        binding: ObserveComingSoonGamesUseCaseImpl
    ): ObserveComingSoonGamesUseCase

    @Binds
    fun bindObserveMostAnticipatedGamesUseCase(
        binding: ObserveMostAnticipatedGamesUseCaseImpl
    ): ObserveMostAnticipatedGamesUseCase

    @Binds
    fun bindObservePopularGamesUseCase(
        binding: ObservePopularGamesUseCaseImpl
    ): ObservePopularGamesUseCase

    @Binds
    fun bindObserveRecentlyReleasedGamesUseCase(
        binding: ObserveRecentlyReleasedGamesUseCaseImpl
    ): ObserveRecentlyReleasedGamesUseCase

    @Binds
    fun bindRefreshComingSoonGamesUseCase(
        binding: RefreshComingSoonGamesUseCaseImpl
    ): RefreshComingSoonGamesUseCase

    @Binds
    fun bindRefreshMostAnticipatedGamesUseCase(
        binding: RefreshMostAnticipatedGamesUseCaseImpl
    ): RefreshMostAnticipatedGamesUseCase

    @Binds
    fun bindRefreshPopularGamesUseCase(
        binding: RefreshPopularGamesUseCaseImpl
    ): RefreshPopularGamesUseCase

    @Binds
    fun bindRefreshRecentlyReleasedGamesUseCase(
        binding: RefreshRecentlyReleasedGamesUseCaseImpl
    ): RefreshRecentlyReleasedGamesUseCase
}

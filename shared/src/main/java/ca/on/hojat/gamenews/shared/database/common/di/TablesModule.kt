package ca.on.hojat.gamenews.shared.database.common.di

import ca.on.hojat.gamenews.shared.database.GameNewsDatabase
import ca.on.hojat.gamenews.core.data.database.articles.ArticlesTable
import ca.on.hojat.gamenews.core.data.database.games.tables.GamesTable
import ca.on.hojat.gamenews.core.data.database.games.tables.LikedGamesTable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object TablesModule {

    @Provides
    @Singleton
    fun provideGamesTable(gameNewsDatabase: GameNewsDatabase): GamesTable {
        return gameNewsDatabase.gamesTable
    }

    @Provides
    @Singleton
    fun provideLikedGamesTable(gameNewsDatabase: GameNewsDatabase): LikedGamesTable {
        return gameNewsDatabase.likedGamesTable
    }

    @Provides
    @Singleton
    fun provideArticlesTable(gameNewsDatabase: GameNewsDatabase): ArticlesTable {
        return gameNewsDatabase.articlesTable
    }
}

package ca.on.hojat.gamehub.core.data.database.common.di

import ca.on.hojat.gamehub.core.data.database.GameHubDatabase
import ca.on.hojat.gamehub.core.data.database.articles.ArticlesTable
import ca.on.hojat.gamehub.core.data.database.games.tables.GamesTable
import ca.on.hojat.gamehub.core.data.database.games.tables.LikedGamesTable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TablesModule {

    @Provides
    @Singleton
    fun provideGamesTable(gameHubDatabase: GameHubDatabase): GamesTable {
        return gameHubDatabase.gamesTable
    }

    @Provides
    @Singleton
    fun provideLikedGamesTable(gameHubDatabase: GameHubDatabase): LikedGamesTable {
        return gameHubDatabase.likedGamesTable
    }

    @Provides
    @Singleton
    fun provideArticlesTable(gameHubDatabase: GameHubDatabase): ArticlesTable {
        return gameHubDatabase.articlesTable
    }
}

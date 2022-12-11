package ca.on.hojat.gamenews.shared.database.common.di

import ca.on.hojat.gamenews.shared.database.GamedgeDatabase
import ca.on.hojat.gamenews.shared.database.articles.tables.ArticlesTable
import ca.on.hojat.gamenews.shared.database.games.tables.GamesTable
import ca.on.hojat.gamenews.shared.database.games.tables.LikedGamesTable
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
    fun provideGamesTable(gamedgeDatabase: GamedgeDatabase): GamesTable {
        return gamedgeDatabase.gamesTable
    }

    @Provides
    @Singleton
    fun provideLikedGamesTable(gamedgeDatabase: GamedgeDatabase): LikedGamesTable {
        return gamedgeDatabase.likedGamesTable
    }

    @Provides
    @Singleton
    fun provideArticlesTable(gamedgeDatabase: GamedgeDatabase): ArticlesTable {
        return gamedgeDatabase.articlesTable
    }
}

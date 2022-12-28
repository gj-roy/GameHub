package ca.on.hojat.gamenews.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.on.hojat.gamenews.core.data.database.articles.ArticlesTable
import ca.on.hojat.gamenews.core.data.database.articles.ArticlesTypeConverter
import ca.on.hojat.gamenews.core.data.database.articles.DbArticle
import ca.on.hojat.gamenews.core.data.database.games.GamesTypeConverter
import ca.on.hojat.gamenews.core.data.database.games.entities.DbGame
import ca.on.hojat.gamenews.core.data.database.games.entities.DbLikedGame
import ca.on.hojat.gamenews.core.data.database.games.tables.GamesTable
import ca.on.hojat.gamenews.core.data.database.games.tables.LikedGamesTable

private const val VERSION = 2

@Database(
    entities = [
        DbGame::class,
        DbLikedGame::class,
        DbArticle::class
    ],
    version = VERSION,
    exportSchema = true,
)
// Seems really strange that I have to specify this annotation here
// with custom provided type converters
@TypeConverters(
    GamesTypeConverter::class,
    ArticlesTypeConverter::class
)
abstract class GameNewsDatabase : RoomDatabase() {
    abstract val gamesTable: GamesTable
    abstract val likedGamesTable: LikedGamesTable
    abstract val articlesTable: ArticlesTable
}

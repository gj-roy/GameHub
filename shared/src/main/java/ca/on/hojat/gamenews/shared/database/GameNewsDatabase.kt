package ca.on.hojat.gamenews.shared.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.on.hojat.gamenews.core.data.database.articles.DbArticle
import ca.on.hojat.gamenews.core.data.database.games.entities.DbGame
import ca.on.hojat.gamenews.core.data.database.games.entities.DbLikedGame
import ca.on.hojat.gamenews.shared.database.articles.ArticlesTypeConverter
import ca.on.hojat.gamenews.core.data.database.articles.ArticlesTable
import ca.on.hojat.gamenews.shared.database.games.GamesTypeConverter
import ca.on.hojat.gamenews.shared.database.games.tables.GamesTable
import ca.on.hojat.gamenews.shared.database.games.tables.LikedGamesTable

@Database(
    entities = [
        DbGame::class,
        DbLikedGame::class,
        DbArticle::class
    ],
    version = Constants.VERSION,
    exportSchema = true,
)
// Seems really strange that I have to specify this annotation here
// with custom provided type converters
@TypeConverters(
    GamesTypeConverter::class,
    ArticlesTypeConverter::class
)
internal abstract class GameNewsDatabase : RoomDatabase() {
    abstract val gamesTable: GamesTable
    abstract val likedGamesTable: LikedGamesTable
    abstract val articlesTable: ArticlesTable
}

package ca.on.hojat.gamehub.core.data.database.common.di

import android.content.Context
import androidx.room.Room
import ca.on.hojat.gamehub.core.data.database.GameHubDatabase
import ca.on.hojat.gamehub.core.data.database.common.MIGRATIONS
import ca.on.hojat.gamehub.core.data.database.common.RoomTypeConverter
import ca.on.hojat.gamehub.core.data.database.common.addTypeConverters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val DATABASE_NAME = "gamehub.db"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    @Suppress("SpreadOperator")
    fun provideGameHubDatabase(
        @ApplicationContext context: Context,
        typeConverters: Set<@JvmSuppressWildcards RoomTypeConverter>
    ): GameHubDatabase {
        return Room.databaseBuilder(
            context,
            GameHubDatabase::class.java,
            DATABASE_NAME
        )
            .addTypeConverters(typeConverters)
            .addMigrations(*MIGRATIONS)
            .build()
    }
}

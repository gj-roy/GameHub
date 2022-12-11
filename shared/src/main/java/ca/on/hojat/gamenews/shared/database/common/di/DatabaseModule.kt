package ca.on.hojat.gamenews.shared.database.common.di

import android.content.Context
import androidx.room.Room
import ca.on.hojat.gamenews.shared.database.Constants
import ca.on.hojat.gamenews.shared.database.GamedgeDatabase
import ca.on.hojat.gamenews.shared.database.common.MIGRATIONS
import ca.on.hojat.gamenews.shared.database.common.RoomTypeConverter
import ca.on.hojat.gamenews.shared.database.common.addTypeConverters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    @Suppress("SpreadOperator")
    fun provideGamedgeDatabase(
        @ApplicationContext context: Context,
        typeConverters: Set<@JvmSuppressWildcards RoomTypeConverter>
    ): GamedgeDatabase {
        return Room.databaseBuilder(
            context,
            GamedgeDatabase::class.java,
            Constants.DATABASE_NAME
        )
            .addTypeConverters(typeConverters)
            .addMigrations(*MIGRATIONS)
            .build()
    }
}

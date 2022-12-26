package ca.on.hojat.gamenews.shared.database.common.di

import android.content.Context
import androidx.room.Room
import ca.on.hojat.gamenews.core.data.database.Constants
import ca.on.hojat.gamenews.core.data.database.common.MIGRATIONS
import ca.on.hojat.gamenews.shared.database.GameNewsDatabase
import ca.on.hojat.gamenews.core.data.database.common.RoomTypeConverter
import ca.on.hojat.gamenews.core.data.database.common.addTypeConverters
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
    fun provideGameNewsDatabase(
        @ApplicationContext context: Context,
        typeConverters: Set<@JvmSuppressWildcards RoomTypeConverter>
    ): GameNewsDatabase {
        return Room.databaseBuilder(
            context,
            GameNewsDatabase::class.java,
            Constants.DATABASE_NAME
        )
            .addTypeConverters(typeConverters)
            .addMigrations(*MIGRATIONS)
            .build()
    }
}

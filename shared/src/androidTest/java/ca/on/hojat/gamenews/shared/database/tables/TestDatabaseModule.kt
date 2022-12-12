package ca.on.hojat.gamenews.shared.database.tables

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import ca.on.hojat.gamenews.shared.database.GameNewsDatabase
import ca.on.hojat.gamenews.shared.database.common.MIGRATIONS
import ca.on.hojat.gamenews.shared.database.common.RoomTypeConverter
import ca.on.hojat.gamenews.shared.database.common.addTypeConverters
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

@Module
@DisableInstallInCheck
internal object TestDatabaseModule {

    @Provides
    @Singleton
    fun provideGamedgeDatabase(
        typeConverters: Set<@JvmSuppressWildcards RoomTypeConverter>
    ): GameNewsDatabase {
        return Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GameNewsDatabase::class.java
        )
            .addTypeConverters(typeConverters)
            .addMigrations(*MIGRATIONS)
            .build()
    }
}

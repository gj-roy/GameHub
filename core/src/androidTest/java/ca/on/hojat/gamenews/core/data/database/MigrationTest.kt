package ca.on.hojat.gamenews.core.data.database

import androidx.room.testing.MigrationTestHelper
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidRule
import ca.on.hojat.gamenews.core.data.database.Constants
import ca.on.hojat.gamenews.core.data.database.GameNewsDatabase
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Provider

// https://developer.android.com/training/data-storage/room/migrating-db-versions
@HiltAndroidTest
internal class MigrationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val migrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        GameNewsDatabase::class.java,
    )

    @Inject
    lateinit var databaseProvider: Provider<GameNewsDatabase>

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun run_all_migrations() {
        migrationTestHelper.createDatabase(Constants.DATABASE_NAME, 1).close()

        databaseProvider.get().apply {
            openHelper.writableDatabase
            close()
        }
    }
}

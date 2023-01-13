package ca.on.hojat.gamenews.data.database.tables

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import ca.on.hojat.gamenews.data.database.DB_ARTICLES
import ca.on.hojat.gamenews.core.data.database.articles.ArticlesTable
import ca.on.hojat.gamenews.core.data.database.articles.DbArticle
import ca.on.hojat.gamenews.core.data.database.common.di.DatabaseModule
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
internal class
ArticlesTableTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val executorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var sut: ArticlesTable

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun saves_and_observes_sorted_articles() {
        runTest {
            sut.saveArticles(DB_ARTICLES)

            val expectedArticles = DB_ARTICLES.sortedByDescending(DbArticle::publicationDate)

            sut.observeArticles(offset = 0, limit = DB_ARTICLES.size).test {
                assertThat(awaitItem()).isEqualTo(expectedArticles)
            }
        }
    }
}

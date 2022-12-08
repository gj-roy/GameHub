package com.paulrybitskyi.gamedge.feature.news.data.throttling

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import ca.on.hojat.gamenews.shared.core.providers.TimestampProvider
import com.paulrybitskyi.gamedge.feature.news.domain.throttling.ArticlesRefreshingThrottler
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@BindType
internal class ArticlesRefreshingThrottlerImpl @Inject constructor(
    private val articlesPreferences: DataStore<Preferences>,
    private val timestampProvider: TimestampProvider
) : ArticlesRefreshingThrottler {

    private companion object {
        private val ARTICLES_REFRESH_TIMEOUT = TimeUnit.MINUTES.toMillis(10L)
    }

    override suspend fun canRefreshArticles(key: String): Boolean {
        return articlesPreferences.data
            .map { it[longPreferencesKey(key)] ?: 0L }
            .map { timestampProvider.getUnixTimestamp() > (it + ARTICLES_REFRESH_TIMEOUT) }
            .first()
    }

    override suspend fun updateArticlesLastRefreshTime(key: String) {
        articlesPreferences.edit {
            it[longPreferencesKey(key)] = timestampProvider.getUnixTimestamp()
        }
    }
}

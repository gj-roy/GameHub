package ca.on.hojat.gamehub.core.data.api.common

import android.content.Context
import ca.on.hojat.gamehub.R
import ca.on.hojat.gamehub.core.extensions.getPackageInfoSafely
import ca.on.hojat.gamehub.core.providers.StringProvider
import com.paulrybitskyi.hiltbinder.BindType
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * As much as I found out, it just returns a String containing
 * app's name and version.
 */
interface UserAgentProvider {

    fun getUserAgent(): String
}

@BindType
internal class UserAgentProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val stringProvider: StringProvider
) : UserAgentProvider {

    override fun getUserAgent(): String {
        val appName = stringProvider.getString(R.string.app_name)
        val versionName = getVersionName()
        val userAgent = buildString {
            append(appName)

            if (versionName != null) {
                append("/$versionName")
            }
        }

        return userAgent
    }

    private fun getVersionName(): String? {
        return context.packageManager.getPackageInfoSafely(context).versionName
    }
}

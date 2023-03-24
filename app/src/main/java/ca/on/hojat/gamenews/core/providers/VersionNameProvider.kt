package ca.on.hojat.gamenews.core.providers

import android.content.Context
import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.core.extensions.getPackageInfoSafely
import com.paulrybitskyi.hiltbinder.BindType
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Provides the "versionName" of GameHub app.
 */
interface VersionNameProvider {
    fun getVersionName(): String
}

@BindType
internal class VersionNameProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val stringProvider: StringProvider,
) : VersionNameProvider {

    override fun getVersionName(): String {
        return stringProvider.getString(
            R.string.version_name_template,
            context.packageManager.getPackageInfoSafely(context).versionName
        )
    }
}

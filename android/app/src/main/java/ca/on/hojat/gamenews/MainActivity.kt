package ca.on.hojat.gamenews

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import ca.on.hojat.gamenews.presentation.theme.GameHubTheme
import ca.on.hojat.gamenews.core.domain.common.usecases.execute
import ca.on.hojat.gamenews.core.downloader.Downloader
import ca.on.hojat.gamenews.core.providers.NetworkStateProvider
import ca.on.hojat.gamenews.core.sharers.TextSharer
import ca.on.hojat.gamenews.core.urlopeners.UrlOpener
import ca.on.hojat.gamenews.feature_settings.domain.entities.Settings
import ca.on.hojat.gamenews.feature_settings.domain.entities.Theme
import ca.on.hojat.gamenews.feature_settings.domain.usecases.ObserveThemeUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import timber.log.Timber
import android.provider.Settings as SystemSettings
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private const val OVERLAY_PERMISSION_REQ_CODE = 1
    }

    @Inject
    lateinit var urlOpener: UrlOpener

    @Inject
    lateinit var textSharer: TextSharer

    @Inject
    lateinit var networkStateProvider: NetworkStateProvider

    @Inject
    lateinit var downloader: Downloader

    @Inject
    lateinit var observeThemeUseCase: ObserveThemeUseCase

    private var shouldKeepSplashOpen = true

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (SystemSettings.canDrawOverlays(this).not()) {
                    // SYSTEM_ALERT_WINDOW permission not granted
                    Timber.e("User didn't grant the permission for overlaying window.")
                }
            }
        }
//        reactInstanceManager?.onActivityResult(this, requestCode, resultCode, data)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setupSplashScreen()
        super.onCreate(savedInstanceState)
        setupSystemBars()
        setupCompose()
        checkOverlayPermission()
    }

    /**
     * In debug build of the app, this function will check if the app has permission to open up
     * debug window in front of other windows (user has to give this permission). Should not be included in
     * release build of the app.
     */
    private fun checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (SystemSettings.canDrawOverlays(this).not()) {
                val intent = Intent(
                    SystemSettings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package: $packageName")
                )
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }
        }

    }

    private fun setupSplashScreen() {
        // Waiting until the app's theme is loaded first before
        // displaying the dashboard to prevent the user from seeing
        // the app blinking as a result of the theme change
        installSplashScreen().setKeepOnScreenCondition(::shouldKeepSplashOpen)
    }

    private fun setupSystemBars() {
        // To be able to draw behind system bars & change their colors
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun setupCompose() {
        setContent {
            CompositionLocalProvider(LocalUrlOpener provides urlOpener) {
                CompositionLocalProvider(LocalTextSharer provides textSharer) {
                    CompositionLocalProvider(LocalNetworkStateProvider provides networkStateProvider) {
                        CompositionLocalProvider(LocalDownloader provides downloader) {
                            GameHubTheme(useDarkTheme = shouldUseDarkTheme()) {
                                MainScreen()
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun shouldUseDarkTheme(): Boolean {
        val themeState = observeThemeUseCase.execute().collectAsState(initial = null)
        val theme = (themeState.value ?: Settings.DEFAULT.theme)

        LaunchedEffect(Unit) {
            snapshotFlow { themeState.value }
                .filterNotNull()
                .collect {
                    if (shouldKeepSplashOpen) {
                        shouldKeepSplashOpen = false
                    }
                }
        }

        return when (theme) {
            Theme.LIGHT -> false
            Theme.DARK -> true
            Theme.SYSTEM -> isSystemInDarkTheme()
        }
    }
}

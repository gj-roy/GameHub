package ca.on.hojat.gamenews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import ca.on.hojat.gamenews.feature_settings.domain.usecases.ObserveThemeUseCase
import ca.on.hojat.gamenews.shared.core.providers.NetworkStateProvider
import ca.on.hojat.gamenews.shared.core.sharers.TextSharer
import ca.on.hojat.gamenews.shared.core.urlopener.UrlOpener
import ca.on.hojat.gamenews.shared.domain.common.usecases.execute
import ca.on.hojat.gamenews.shared.ui.LocalNetworkStateProvider
import ca.on.hojat.gamenews.shared.ui.LocalTextSharer
import ca.on.hojat.gamenews.shared.ui.LocalUrlOpener
import ca.on.hojat.gamenews.shared.ui.theme.GameNewsTheme
import ca.on.hojat.gamenews.shared.extensions.intentFor
import ca.on.hojat.gamenews.feature_settings.domain.entities.Settings
import ca.on.hojat.gamenews.feature_settings.domain.entities.Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {

        fun newIntent(context: Context): Intent {
            return context.intentFor<MainActivity>()
        }
    }

    @Inject
    lateinit var urlOpener: UrlOpener

    @Inject
    lateinit var textSharer: TextSharer

    @Inject
    lateinit var networkStateProvider: NetworkStateProvider

    @Inject
    lateinit var observeThemeUseCase: ObserveThemeUseCase

    private var shouldKeepSplashOpen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        setupSplashScreen()
        super.onCreate(savedInstanceState)
        setupSystemBars()
        setupCompose()
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
                        GameNewsTheme(useDarkTheme = shouldUseDarkTheme()) {
                            MainScreen()
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

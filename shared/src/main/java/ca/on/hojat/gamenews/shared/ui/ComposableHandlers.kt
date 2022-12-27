package ca.on.hojat.gamenews.shared.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import ca.on.hojat.gamenews.core.extensions.showLongToast
import ca.on.hojat.gamenews.core.extensions.showShortToast
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ca.on.hojat.gamenews.shared.ui.base.BaseViewModel
import ca.on.hojat.gamenews.core.common_ui.base.events.Command
import ca.on.hojat.gamenews.core.common_ui.base.events.Route
import ca.on.hojat.gamenews.core.common_ui.base.events.GeneralCommand
import ca.on.hojat.gamenews.shared.ui.theme.GameNewsTheme
import ca.on.hojat.gamenews.shared.ui.theme.navBar

@Composable
fun CommandsHandler(
    viewModel: BaseViewModel,
    onHandleCommand: ((Command) -> Unit)? = null,
) {
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.commandFlow.collect { command ->
            when (command) {
                is GeneralCommand.ShowShortToast -> context.showShortToast(command.message)
                is GeneralCommand.ShowLongToast -> context.showLongToast(command.message)
                else -> onHandleCommand?.invoke(command)
            }
        }
    }
}

@Composable
fun RoutesHandler(
    viewModel: BaseViewModel,
    onRoute: (Route) -> Unit,
) {
    LaunchedEffect(viewModel) {
        viewModel.routeFlow
            .collect(onRoute)
    }
}

@Composable
fun NavBarColorHandler() {
    val systemUiController = rememberSystemUiController()
    val navBarColor = GameNewsTheme.colors.navBar

    LaunchedEffect(navBarColor) {
        systemUiController.setNavigationBarColor(navBarColor)
    }
}

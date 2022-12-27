package ca.on.hojat.gamenews.shared.ui.base.events.common

import ca.on.hojat.gamenews.core.common_ui.base.events.Command

sealed class GeneralCommand : Command {
    class ShowShortToast(val message: String) : GeneralCommand()
    class ShowLongToast(val message: String) : GeneralCommand()
}

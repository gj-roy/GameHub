package ca.on.hojat.gamehub.core.events

sealed class GeneralCommand : Command {
    class ShowShortToast(val message: String) : GeneralCommand()
    class ShowLongToast(val message: String) : GeneralCommand()
}

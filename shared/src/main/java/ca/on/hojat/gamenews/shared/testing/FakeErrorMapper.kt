package ca.on.hojat.gamenews.shared.testing

import ca.on.hojat.gamenews.shared.core.ErrorMapper

class FakeErrorMapper : ErrorMapper {

    override fun mapToMessage(error: Throwable): String {
        return "error"
    }
}

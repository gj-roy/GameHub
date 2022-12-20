package ca.on.hojat.gamenews.shared.testing

import ca.on.hojat.gamenews.core.mappers.ErrorMapper

class FakeErrorMapper : ErrorMapper {

    override fun mapToMessage(error: Throwable): String {
        return "error"
    }
}

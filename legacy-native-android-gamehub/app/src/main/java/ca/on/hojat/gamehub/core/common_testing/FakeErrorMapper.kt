package ca.on.hojat.gamehub.core.common_testing

import ca.on.hojat.gamehub.core.mappers.ErrorMapper

class FakeErrorMapper : ErrorMapper {

    override fun mapToMessage(error: Throwable): String {
        return "error"
    }
}

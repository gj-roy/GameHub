package ca.on.hojat.gamenews.shared.domain.common.usecases

import kotlinx.coroutines.flow.Flow

interface ObservableUseCase<In, Out> {
    fun execute(params: In): Flow<Out>
}

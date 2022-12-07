package ca.on.hojat.gamenews.shared.domain.common.extensions

import ca.on.hojat.gamenews.shared.domain.common.usecases.ObservableUseCase
import ca.on.hojat.gamenews.shared.domain.common.usecases.UseCase
import kotlinx.coroutines.flow.Flow

suspend fun <Out> UseCase<Unit, Out>.execute(): Out {
    return execute(Unit)
}

fun <Out> ObservableUseCase<Unit, Out>.execute(): Flow<Out> {
    return execute(Unit)
}

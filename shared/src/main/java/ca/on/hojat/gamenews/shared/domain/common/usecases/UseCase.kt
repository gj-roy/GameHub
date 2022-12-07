package ca.on.hojat.gamenews.shared.domain.common.usecases

interface UseCase<In, Out> {
    suspend fun execute(params: In): Out
}

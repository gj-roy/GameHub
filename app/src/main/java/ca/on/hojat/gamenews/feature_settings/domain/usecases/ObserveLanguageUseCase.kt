package ca.on.hojat.gamenews.feature_settings.domain.usecases

import ca.on.hojat.gamenews.core.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.common.usecases.ObservableUseCase
import ca.on.hojat.gamenews.core.domain.common.usecases.execute
import ca.on.hojat.gamenews.feature_settings.domain.entities.Language
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface ObserveLanguageUseCase : ObservableUseCase<Unit, Language>

@Singleton
@BindType
internal class ObserveLanguageUseCaseImpl @Inject constructor(
    private val observeSettingsUseCase: ObserveSettingsUseCase,
    private val dispatcherProvider: DispatcherProvider,
) : ObserveLanguageUseCase {

    override fun execute(params: Unit): Flow<Language> {
        return observeSettingsUseCase.execute()
            .map { settings -> settings.language }
            .distinctUntilChanged()
            .flowOn(dispatcherProvider.main)
    }
}
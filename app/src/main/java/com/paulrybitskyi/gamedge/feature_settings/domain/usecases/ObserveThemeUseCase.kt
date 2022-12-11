package com.paulrybitskyi.gamedge.feature_settings.domain.usecases

import ca.on.hojat.gamenews.shared.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.shared.domain.common.usecases.ObservableUseCase
import ca.on.hojat.gamenews.shared.domain.common.extensions.execute
import com.paulrybitskyi.gamedge.feature_settings.domain.entities.Theme
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface ObserveThemeUseCase : ObservableUseCase<Unit, Theme>

@Singleton
@BindType
internal class ObserveThemeUseCaseImpl @Inject constructor(
    private val observeSettingsUseCase: ObserveSettingsUseCase,
    private val dispatcherProvider: DispatcherProvider,
) : ObserveThemeUseCase {

    override fun execute(params: Unit): Flow<Theme> {
        return observeSettingsUseCase.execute()
            .map { settings -> settings.theme }
            .distinctUntilChanged()
            .flowOn(dispatcherProvider.main)
    }
}

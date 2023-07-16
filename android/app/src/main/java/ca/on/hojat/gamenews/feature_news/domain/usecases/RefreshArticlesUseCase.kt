package ca.on.hojat.gamenews.feature_news.domain.usecases

import ca.on.hojat.gamenews.core.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.core.extensions.onEachSuccess
import ca.on.hojat.gamenews.core.domain.entities.Pagination
import ca.on.hojat.gamenews.core.domain.common.usecases.ObservableUseCase
import ca.on.hojat.gamenews.feature_news.domain.repository.ArticlesRepository
import ca.on.hojat.gamenews.feature_news.domain.entities.Article
import ca.on.hojat.gamenews.feature_news.domain.throttling.ArticlesRefreshingThrottlerTools
import ca.on.hojat.gamenews.feature_news.domain.usecases.RefreshArticlesUseCase.Params
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * You give it an object of type [Params] and will receive a [Flow<DomainResult<List<Article>>>].
 */
internal interface RefreshArticlesUseCase : ObservableUseCase<Params, DomainResult<List<Article>>> {

    data class Params(val pagination: Pagination = Pagination())
}

@Singleton
@BindType
internal class RefreshArticlesUseCaseImpl @Inject constructor(
    private val articlesRepository: ArticlesRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val throttlerTools: ArticlesRefreshingThrottlerTools,
) : RefreshArticlesUseCase {

    override fun execute(params: Params): Flow<DomainResult<List<Article>>> {
        val throttlerKey = throttlerTools.keyProvider.provideArticlesKey(params.pagination)

        return flow {
            if (throttlerTools.throttler.canRefreshArticles(throttlerKey)) {
                emit(articlesRepository.remote.getArticles(params.pagination))
            }
        }
            .onEachSuccess { articles ->
                articlesRepository.local.saveArticles(articles)
                throttlerTools.throttler.updateArticlesLastRefreshTime(throttlerKey)
            }
            .flowOn(dispatcherProvider.main)
    }
}

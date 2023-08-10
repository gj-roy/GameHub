package ca.on.hojat.gamehub.feature_news.domain.usecases

import ca.on.hojat.gamehub.core.domain.common.DispatcherProvider
import ca.on.hojat.gamehub.core.domain.entities.Pagination
import ca.on.hojat.gamehub.core.domain.common.usecases.ObservableUseCase
import ca.on.hojat.gamehub.feature_news.domain.repository.ArticlesLocalDataSource
import ca.on.hojat.gamehub.feature_news.domain.entities.Article
import ca.on.hojat.gamehub.feature_news.domain.usecases.ObserveArticlesUseCase.Params
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * You'll give it an object of type [Params] and will receive a [Flow<List<Article>>].
 */
internal interface ObserveArticlesUseCase : ObservableUseCase<Params, List<Article>> {

    data class Params(
        val pagination: Pagination = Pagination()
    )
}

@Singleton
@BindType
internal class ObserveArticlesUseCaseImpl @Inject constructor(
    private val articlesLocalDataSource: ArticlesLocalDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : ObserveArticlesUseCase {

    override fun execute(params: Params): Flow<List<Article>> {
        return articlesLocalDataSource.observeArticles(params.pagination)
            .flowOn(dispatcherProvider.main)
    }
}

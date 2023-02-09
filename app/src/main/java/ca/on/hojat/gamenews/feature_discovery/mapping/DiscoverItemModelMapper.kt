package ca.on.hojat.gamenews.feature_discovery.mapping

import ca.on.hojat.gamenews.core.factories.IgdbImageSize
import ca.on.hojat.gamenews.core.factories.IgdbImageUrlFactory
import ca.on.hojat.gamenews.core.domain.entities.Game
import ca.on.hojat.gamenews.feature_discovery.widgets.DiscoverScreenItemData
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject


abstract class HojatDiscoverItemModelMapper {

    /**
     * You give it a [Game] and it will be converted to the normal data we use
     * in discover screen; which is [DiscoverScreenItemData].
     */
    internal abstract fun mapToUiModel(game: Game): DiscoverScreenItemData

    /**
     * You give it a list of [Game]s and they will be converted to a list of [DiscoverScreenItemData].
     */
    internal fun mapToUiModels(
        games: List<Game>,
    ): List<DiscoverScreenItemData> {
        return games.map(::mapToUiModel)
    }
}

@BindType(installIn = BindType.Component.VIEW_MODEL)
internal class DiscoverItemModelMapperImpl @Inject constructor(
    private val igdbImageUrlFactory: IgdbImageUrlFactory
) : HojatDiscoverItemModelMapper() {

    override fun mapToUiModel(game: Game): DiscoverScreenItemData {
        return DiscoverScreenItemData(
            id = game.id,
            title = game.name,
            coverUrl = game.cover?.let { cover ->
                igdbImageUrlFactory.createUrl(
                    cover,
                    IgdbImageUrlFactory.Config(IgdbImageSize.BIG_COVER)
                )
            }
        )
    }
}



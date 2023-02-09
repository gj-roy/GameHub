package ca.on.hojat.gamenews.feature_info.presentation.widgets.videos

import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.core.providers.StringProvider
import ca.on.hojat.gamenews.core.factories.YoutubeMediaUrlFactory
import ca.on.hojat.gamenews.core.factories.YoutubeThumbnailSize
import ca.on.hojat.gamenews.core.domain.entities.Video
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

internal interface InfoScreenVideoUiModelMapper {
    fun mapToUiModel(video: Video): GameInfoVideoUiModel?
}

@BindType(installIn = BindType.Component.VIEW_MODEL)
internal class InfoScreenVideoUiModelMapperImpl @Inject constructor(
    private val youtubeMediaUrlFactory: YoutubeMediaUrlFactory,
    private val stringProvider: StringProvider,
) : InfoScreenVideoUiModelMapper {

    override fun mapToUiModel(video: Video): GameInfoVideoUiModel? {
        val thumbnailUrl = youtubeMediaUrlFactory.createThumbnailUrl(
            video,
            YoutubeThumbnailSize.MEDIUM
        )
        val videoUrl = youtubeMediaUrlFactory.createVideoUrl(video)
        val videoTitle = video.name ?: stringProvider.getString(
            R.string.game_info_video_title_fallback,
        )

        if ((thumbnailUrl == null) && (videoUrl == null)) return null

        return GameInfoVideoUiModel(
            id = video.id,
            thumbnailUrl = checkNotNull(thumbnailUrl),
            videoUrl = checkNotNull(videoUrl),
            title = videoTitle,
        )
    }
}

internal fun InfoScreenVideoUiModelMapper.mapToUiModels(
    videos: List<Video>,
): List<GameInfoVideoUiModel> {
    if (videos.isEmpty()) return emptyList()

    return videos.mapNotNull(::mapToUiModel)
}

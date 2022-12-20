package ca.on.hojat.gamenews.shared.data.games.datastores.igdb

import ca.on.hojat.gamenews.shared.api.igdb.games.entities.ApiAgeRating
import ca.on.hojat.gamenews.shared.api.igdb.games.entities.ApiCategory
import ca.on.hojat.gamenews.shared.api.igdb.games.entities.ApiCompany
import ca.on.hojat.gamenews.shared.api.igdb.games.entities.ApiGame
import ca.on.hojat.gamenews.shared.api.igdb.games.entities.ApiGenre
import ca.on.hojat.gamenews.shared.api.igdb.games.entities.ApiImage
import ca.on.hojat.gamenews.shared.api.igdb.games.entities.ApiInvolvedCompany
import ca.on.hojat.gamenews.shared.api.igdb.games.entities.ApiKeyword
import ca.on.hojat.gamenews.shared.api.igdb.games.entities.ApiMode
import ca.on.hojat.gamenews.shared.api.igdb.games.entities.ApiPlatform
import ca.on.hojat.gamenews.shared.api.igdb.games.entities.ApiPlayerPerspective
import ca.on.hojat.gamenews.shared.api.igdb.games.entities.ApiReleaseDate
import ca.on.hojat.gamenews.shared.api.igdb.games.entities.ApiTheme
import ca.on.hojat.gamenews.shared.api.igdb.games.entities.ApiVideo
import ca.on.hojat.gamenews.shared.api.igdb.games.entities.ApiWebsite
import ca.on.hojat.gamenews.core.domain.entities.AgeRating
import ca.on.hojat.gamenews.core.domain.entities.AgeRatingCategory
import ca.on.hojat.gamenews.core.domain.entities.AgeRatingType
import ca.on.hojat.gamenews.core.domain.entities.Category
import ca.on.hojat.gamenews.core.domain.entities.Company
import ca.on.hojat.gamenews.core.domain.entities.Game
import ca.on.hojat.gamenews.core.domain.entities.Genre
import ca.on.hojat.gamenews.core.domain.entities.Image
import ca.on.hojat.gamenews.core.domain.entities.InvolvedCompany
import ca.on.hojat.gamenews.core.domain.entities.Keyword
import ca.on.hojat.gamenews.core.domain.entities.Mode
import ca.on.hojat.gamenews.core.domain.entities.Platform
import ca.on.hojat.gamenews.core.domain.entities.PlayerPerspective
import ca.on.hojat.gamenews.core.domain.entities.ReleaseDate
import ca.on.hojat.gamenews.core.domain.entities.ReleaseDateCategory
import ca.on.hojat.gamenews.core.domain.entities.Theme
import ca.on.hojat.gamenews.core.domain.entities.Video
import ca.on.hojat.gamenews.core.domain.entities.Website
import ca.on.hojat.gamenews.core.domain.entities.WebsiteCategory
import javax.inject.Inject

internal class IgdbGameMapper @Inject constructor() {

    fun mapToDomainGame(apiGame: ApiGame): Game {
        return Game(
            id = apiGame.id,
            followerCount = apiGame.followerCount,
            hypeCount = apiGame.hypeCount,
            releaseDate = apiGame.releaseDate,
            criticsRating = apiGame.criticsRating,
            usersRating = apiGame.usersRating,
            totalRating = apiGame.totalRating,
            name = apiGame.name,
            summary = apiGame.summary,
            storyline = apiGame.storyline,
            category = apiGame.category.toDomainCategory(),
            cover = apiGame.cover?.toDomainImage(),
            releaseDates = apiGame.releaseDates.toDomainReleaseDates(),
            ageRatings = apiGame.ageRatings.toDomainAgeRatings(),
            videos = apiGame.videos.toDomainVideos(),
            artworks = apiGame.artworks.toDomainImages(),
            screenshots = apiGame.screenshots.toDomainImages(),
            genres = apiGame.genres.toDomainGenres(),
            platforms = apiGame.platforms.toDomainPlatforms(),
            playerPerspectives = apiGame.playerPerspectives.toDomainPlayerPerspectives(),
            themes = apiGame.themes.toDomainThemes(),
            modes = apiGame.modes.toDomainModes(),
            keywords = apiGame.keywords.toDomainKeywords(),
            involvedCompanies = apiGame.involvedCompanies.toDomainInvolvedCompanies(),
            websites = apiGame.websites.toDomainWebsites(),
            similarGames = apiGame.similarGames,
        )
    }

    private fun ApiCategory.toDomainCategory(): Category {
        return Category.valueOf(name)
    }

    private fun ApiImage.toDomainImage(): Image {
        return Image(
            id = id,
            width = width,
            height = height,
        )
    }

    private fun List<ApiImage>.toDomainImages(): List<Image> {
        return map { it.toDomainImage() }
    }

    private fun List<ApiVideo>.toDomainVideos(): List<Video> {
        return map {
            Video(
                id = it.id,
                name = it.name,
            )
        }
    }

    private fun List<ApiReleaseDate>.toDomainReleaseDates(): List<ReleaseDate> {
        return map {
            ReleaseDate(
                date = it.date,
                year = it.year,
                category = ReleaseDateCategory.valueOf(it.category.name),
            )
        }
    }

    private fun List<ApiAgeRating>.toDomainAgeRatings(): List<AgeRating> {
        return map {
            AgeRating(
                category = AgeRatingCategory.valueOf(it.category.name),
                type = AgeRatingType.valueOf(it.type.name),
            )
        }
    }

    private fun List<ApiGenre>.toDomainGenres(): List<Genre> {
        return map {
            Genre(
                name = it.name,
            )
        }
    }

    private fun List<ApiPlatform>.toDomainPlatforms(): List<Platform> {
        return map {
            Platform(
                abbreviation = it.abbreviation,
                name = it.name,
            )
        }
    }

    private fun List<ApiPlayerPerspective>.toDomainPlayerPerspectives(): List<PlayerPerspective> {
        return map {
            PlayerPerspective(
                name = it.name,
            )
        }
    }

    private fun List<ApiTheme>.toDomainThemes(): List<Theme> {
        return map {
            Theme(
                name = it.name,
            )
        }
    }

    private fun List<ApiMode>.toDomainModes(): List<Mode> {
        return map {
            Mode(
                name = it.name,
            )
        }
    }

    private fun List<ApiKeyword>.toDomainKeywords(): List<Keyword> {
        return map {
            Keyword(
                name = it.name,
            )
        }
    }

    private fun List<ApiInvolvedCompany>.toDomainInvolvedCompanies(): List<InvolvedCompany> {
        return map {
            InvolvedCompany(
                company = it.company.toDomainCompany(),
                isDeveloper = it.isDeveloper,
                isPublisher = it.isPublisher,
                isPorter = it.isPorter,
                isSupporting = it.isSupporting,
            )
        }
    }

    private fun ApiCompany.toDomainCompany(): Company {
        return Company(
            id = id,
            name = name,
            websiteUrl = websiteUrl,
            logo = logo?.toDomainImage(),
            developedGames = developedGames,
        )
    }

    private fun List<ApiWebsite>.toDomainWebsites(): List<Website> {
        return map {
            Website(
                id = it.id,
                url = it.url,
                category = WebsiteCategory.valueOf(it.category.name),
            )
        }
    }
}

internal fun IgdbGameMapper.mapToDomainGames(apiGames: List<ApiGame>): List<Game> {
    return apiGames.map(::mapToDomainGame)
}

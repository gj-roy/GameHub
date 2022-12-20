package ca.on.hojat.gamenews.feature_info.presentation.widgets.companies

import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.core.providers.StringProvider
import ca.on.hojat.gamenews.shared.core.factories.IgdbImageExtension
import ca.on.hojat.gamenews.shared.core.factories.IgdbImageSize
import ca.on.hojat.gamenews.shared.core.factories.IgdbImageUrlFactory
import ca.on.hojat.gamenews.core.domain.entities.InvolvedCompany
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

internal interface GameInfoCompanyUiModelMapper {
    fun mapToUiModel(company: InvolvedCompany): GameInfoCompanyUiModel
}

@BindType(installIn = BindType.Component.VIEW_MODEL)
internal class GameInfoCompanyUiModelMapperImpl @Inject constructor(
    private val igdbImageUrlFactory: IgdbImageUrlFactory,
    private val stringProvider: StringProvider,
) : GameInfoCompanyUiModelMapper {

    private companion object {
        private const val COMPANY_ROLE_SEPARATOR = ", "
    }

    override fun mapToUiModel(company: InvolvedCompany): GameInfoCompanyUiModel {
        return GameInfoCompanyUiModel(
            id = company.company.id,
            logoUrl = company.createLogoUrl(),
            logoWidth = company.company.logo?.width,
            logoHeight = company.company.logo?.height,
            websiteUrl = company.company.websiteUrl,
            name = company.company.name,
            roles = company.createRolesString()
        )
    }

    private fun InvolvedCompany.createLogoUrl(): String? {
        return company.logo?.let { image ->
            igdbImageUrlFactory.createUrl(
                image,
                IgdbImageUrlFactory.Config(IgdbImageSize.HD, IgdbImageExtension.PNG)
            )
        }
    }

    private fun InvolvedCompany.createRolesString(): String {
        return buildList {
            if (isDeveloper) add(R.string.company_role_developer)
            if (isPublisher) add(R.string.company_role_publisher)
            if (isPorter) add(R.string.company_role_porter)
            if (isSupporting) add(R.string.company_role_supporting)
        }
            .joinToString(
                separator = COMPANY_ROLE_SEPARATOR,
                transform = stringProvider::getString
            )
    }
}

internal fun GameInfoCompanyUiModelMapper.mapToUiModels(
    companies: List<InvolvedCompany>,
): List<GameInfoCompanyUiModel> {
    if (companies.isEmpty()) return emptyList()

    val comparator = compareByDescending(InvolvedCompany::isDeveloper)
        .thenByDescending(InvolvedCompany::isPublisher)
        .thenByDescending(InvolvedCompany::isPorter)
        .thenByDescending { it.company.hasLogo }

    return companies
        .sortedWith(comparator)
        .map(::mapToUiModel)
}

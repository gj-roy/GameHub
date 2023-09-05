import {IgdbService} from "./IgdbService";
import {ApiGamePreview} from "./entities/ApiGamePreview";
import {RequestFieldsRepository} from "./RequestFieldsRepository";

/**
 * This data source is for getting preview information for lots of games.
 * At the time of writing, this DS is only used in DiscoverScreen.
 */
export class RemoteGamePreviewsDataSource {

    constructor() {
    }

    getPopularGames(): Promise<ApiGamePreview[]> {
        const requestFieldsRepository = new RequestFieldsRepository();
        return IgdbService(`${requestFieldsRepository.getPreviewGamesRequestFields()};where rating != null;where first_release_date > 1538129354;limit 20;sort total_rating desc;`);
    }

    getRecentlyReleasedGames(): Promise<ApiGamePreview[]> {
        const requestFieldsRepository = new RequestFieldsRepository();
        return IgdbService(`${requestFieldsRepository.getPreviewGamesRequestFields()};where first_release_date > 1538129354 & first_release_date < 1691193575 ;limit 20;sort first_release_date desc;`);
    }

    getComingSoonGames(): Promise<ApiGamePreview[]> {
        const requestFieldsRepository = new RequestFieldsRepository();
        return IgdbService(`${requestFieldsRepository.getPreviewGamesRequestFields()};where first_release_date > 1691193575 ;limit 20;sort first_release_date asc;`);
    }

    getMostAnticipatedGames(): Promise<ApiGamePreview[]> {
        const requestFieldsRepository = new RequestFieldsRepository();
        return IgdbService(`${requestFieldsRepository.getPreviewGamesRequestFields()};where first_release_date > 1691193575 & hypes != null ;limit 20;sort hypes desc;`);
    }
}

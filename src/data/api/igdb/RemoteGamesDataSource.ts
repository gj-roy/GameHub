import {IgdbService} from "./IgdbService";
import {getAllRequestFields, getPreviewGamesRequestFields} from "./RequestFieldsRepository";
import {ApiGame} from "./entities/ApiGame";
import {ApiGamePreview} from "./entities/ApiGamePreview";

export class RemoteGamesDataSource {

    constructor() {
    }

    getPopularGames(): Promise<ApiGamePreview[]> {

        return IgdbService(`${getPreviewGamesRequestFields()};where rating != null;where first_release_date > 1538129354;limit 20;sort total_rating desc;`);
    }

    getRecentlyReleasedGames(): Promise<ApiGamePreview[]> {

        return IgdbService(`${getPreviewGamesRequestFields()};where first_release_date > 1538129354 & first_release_date < 1691193575 ;limit 20;sort first_release_date desc;`);
    }

    getComingSoonGames(): Promise<ApiGamePreview[]> {

        return IgdbService(`${getPreviewGamesRequestFields()};where first_release_date > 1691193575 ;limit 20;sort first_release_date asc;`);
    }

    getMostAnticipatedGames(): Promise<ApiGamePreview[]> {
        return IgdbService(`${getPreviewGamesRequestFields()};where first_release_date > 1691193575 & hypes != null ;limit 20;sort hypes desc;`);
    }

    /**
     * @param id the specific game that we want to get all the information about.
     *
     * @returns games a promised list of ApiGames, but it has just 1
     * item in it.
     */
    getSpecificGameDetails(id: number): Promise<ApiGame[]> {
        return IgdbService(`${getAllRequestFields()};where id = ${id};`);
    }
}

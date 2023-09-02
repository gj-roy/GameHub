import {IgdbService} from "./IgdbService";
import {getAllRequestFields} from "./RequestFieldsRepository";
import {ApiGame} from "./entities/ApiGame";

export class RemotePopularGamesDataSource {

    constructor() {
    }

    getPopularGames(): Promise<ApiGame[]> {

        return IgdbService(`${getAllRequestFields()};where rating != null;where first_release_date > 1538129354;limit 20;sort total_rating desc;`);
    }
}

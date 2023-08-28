import {IgdbService} from "./IgdbService";
import {getAllRequestFields} from "./RequestFieldsRepository";

export class RemotePopularGamesDataSource {

    constructor() {
    }

    getPopularGames() {

        return IgdbService(`${getAllRequestFields()};where rating != null;where first_release_date > 1538129354;limit 20;sort total_rating desc;`);
    }
}

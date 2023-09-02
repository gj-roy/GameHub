import {IgdbService} from "./IgdbService";
import {getAllRequestFields} from "./RequestFieldsRepository";
import {ApiGame} from "./entities/ApiGame";

export class RemoteRecentlyReleasedGamesDataSource {

    constructor() {
    }

    getRecentlyReleasedGames(): Promise<ApiGame[]> {

        return IgdbService(`${getAllRequestFields()};where first_release_date > 1538129354 & first_release_date < 1691193575 ;limit 20;sort first_release_date desc;`);
    }
}

import {IgdbService} from "./IgdbService";
import {getAllRequestFields} from "./RequestFieldsRepository";

export class RemoteRecentlyReleasedGamesDataSource {

    constructor() {
    }

    getRecentlyReleasedGames() {

        return IgdbService(`${getAllRequestFields()};where first_release_date > 1538129354 & first_release_date < 1691193575 ;limit 20;sort first_release_date desc;`);
    }
}

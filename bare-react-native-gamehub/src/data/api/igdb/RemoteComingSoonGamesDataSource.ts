import {IgdbService} from "./IgdbService";
import {getAllRequestFields} from "./RequestFieldsRepository";

export class RemoteComingSoonGamesDataSource {

    constructor() {
    }

    getComingSoonGames() {

        return IgdbService(`${getAllRequestFields()};where first_release_date > 1691193575 ;limit 20;sort first_release_date asc;`);
    }
}

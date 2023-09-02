import {IgdbService} from "./IgdbService";
import {getAllRequestFields} from "./RequestFieldsRepository";
import {ApiGame} from "./entities/ApiGame";

export class RemoteComingSoonGamesDataSource {

    constructor() {
    }

    getComingSoonGames(): Promise<ApiGame[]> {

        return IgdbService(`${getAllRequestFields()};where first_release_date > 1691193575 ;limit 20;sort first_release_date asc;`);
    }
}

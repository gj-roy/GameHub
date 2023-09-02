import {IgdbService} from "./IgdbService";
import {getAllRequestFields} from "./RequestFieldsRepository";
import {ApiGame} from "./entities/ApiGame";

export class RemoteMostAnticipatedGamesDataSource {

    constructor() {
    }

    getMostAnticipatedGames(): Promise<ApiGame[]> {
        return IgdbService(`${getAllRequestFields()};where first_release_date > 1691193575 & hypes != null ;limit 20;sort hypes desc;`);
    }
}

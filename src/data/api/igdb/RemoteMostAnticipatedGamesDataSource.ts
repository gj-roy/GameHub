import {IgdbService} from "./IgdbService";
import {getAllRequestFields} from "./RequestFieldsRepository";

export class RemoteMostAnticipatedGamesDataSource {

    constructor() {
    }

    getMostAnticipatedGames() {
        return IgdbService(`${getAllRequestFields()};where first_release_date > 1691193575 & hypes != null ;limit 10;sort hypes desc;`);
    }
}

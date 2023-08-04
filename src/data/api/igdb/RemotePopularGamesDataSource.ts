import {IGDB_API_BASE_URL} from "../../../secrets/Secrets";
import {IgdbService} from "./IgdbService";


export class RemotePopularGamesDataSource {

    constructor() {
    }

    getPopularGames() {
        return IgdbService();
    }
}

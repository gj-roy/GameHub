import {ApiGame} from "./entities/ApiGame";
import {gamesServerResponse} from "./FakeGamesServer";

/**
 * This data source is for getting the detailed API
 * game information for any specific given game.
 */
export class RemoteGameDataSource {

    constructor() {
    }

    getFakeGameData(id: number): ApiGame {
        return gamesServerResponse.filter(apiGame => apiGame.id === id)[0];
    }
}

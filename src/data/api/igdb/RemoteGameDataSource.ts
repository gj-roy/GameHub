import {ApiGame} from "./entities/ApiGame";
import {IgdbService} from "./IgdbService";
import {RequestFieldsRepository} from "./RequestFieldsRepository";

/**
 * This data source is for getting the detailed API
 * game information for any specific given game.
 */
export class RemoteGameDataSource {

    constructor() {
    }

    /**
     * @param id the specific game that we want to get all the information about.
     *
     * @returns games a promised list of ApiGames, but it has just 1
     * item in it.
     */
    getSpecificGameDetails(id: number): Promise<ApiGame[]> {
        const requestFieldsRepository = new RequestFieldsRepository();
        return IgdbService(`${requestFieldsRepository.getAllRequestFields()};where id = ${id};`);
    }
}

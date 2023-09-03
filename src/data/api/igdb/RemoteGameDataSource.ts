import {ApiGame} from "./entities/ApiGame";
import {IgdbService} from "./IgdbService";
import {getAllRequestFields} from "./RequestFieldsRepository";

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
        return IgdbService(`${getAllRequestFields()};where id = ${id};`);
    }
}

import {IGDB_API_BASE_URL} from "../../../secrets/Secrets";
import {IgdbService} from "./IgdbService";
import {AxiosRequestConfig} from "axios";

export class RemoteMostAnticipatedGamesDataSource {

    constructor() {
    }

    getMostAnticipatedGames() {

        const mostAnticipatedGamesRequestConfig: AxiosRequestConfig = {

            url: '/games',
            method: 'POST',
            baseURL: IGDB_API_BASE_URL,
            headers: {
                'Client-ID': 'g88ty1lcsaf2ewvqqpyxngpssbvhsq',
                'Authorization': 'Bearer g92h6pqhvsmiczksjvvjd6weswhc5o',
            },
            timeout: 10_000,
            responseType: 'json',
            data: 'fields *;where first_release_date > 1691193575 & hypes != null ;limit 50;sort hypes desc;',

        };
        return IgdbService(mostAnticipatedGamesRequestConfig);
    }
}

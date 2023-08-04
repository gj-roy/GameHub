import {IGDB_API_BASE_URL} from "../../../secrets/Secrets";
import {IgdbService} from "./IgdbService";
import {AxiosRequestConfig} from "axios";

export class RemotePopularGamesDataSource {

    constructor() {
    }

    getPopularGames() {

        const PopularGamesRequestConfig: AxiosRequestConfig = {

            url: '/games',
            method: 'POST',
            baseURL: IGDB_API_BASE_URL,
            headers: {
                'Client-ID': 'g88ty1lcsaf2ewvqqpyxngpssbvhsq',
                'Authorization': 'Bearer g92h6pqhvsmiczksjvvjd6weswhc5o',
            },
            timeout: 10_000,
            responseType: 'json',
            data: 'fields *;where rating != null;where first_release_date > 1538129354;limit 50;sort total_rating desc;',

        };
        return IgdbService(PopularGamesRequestConfig);
    }
}

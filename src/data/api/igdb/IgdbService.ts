import {IGDB_API_BASE_URL} from "../../../secrets/Secrets";
import axios, {AxiosRequestConfig} from "axios";
import {ApiGame} from "./entities/ApiGame";


export const IgdbService: () => Promise<ApiGame[]> = async () => {

    const requestParams: AxiosRequestConfig = {

        url: '/games',
        method: 'POST',
        baseURL: IGDB_API_BASE_URL,
        headers: {
            'Accept': 'application/json',
            'Client-ID': 'g88ty1lcsaf2ewvqqpyxngpssbvhsq',
            'Authorization': 'Bearer g92h6pqhvsmiczksjvvjd6weswhc5o',
        },
        timeout: 10_000,
        responseType: 'json',
        data: 'fields *;where rating != null;where first_release_date > 1538129354;limit 50;sort total_rating desc;',

    };

    try {
        const response = await axios.request(requestParams);
        return response.data;
    } catch (error) {
        console.error('error: ', error);
        return null;
    }
};

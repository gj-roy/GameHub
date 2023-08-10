import axios, {AxiosRequestConfig} from "axios";
import {ApiGame} from "./entities/ApiGame";
import {IGDB_API_BASE_URL} from "../../../secrets/Secrets";

export const IgdbService: (requestFields: string) => Promise<ApiGame[]> =
    async (requestFields: string) => {

        const requestConfig: AxiosRequestConfig = {
            url: '/games',
            method: 'POST',
            baseURL: IGDB_API_BASE_URL,
            headers: {
                'Client-ID': 'g88ty1lcsaf2ewvqqpyxngpssbvhsq',
                'Authorization': 'Bearer rv84j08pceq1yk51geqb75s3gym5eg',
            },
            timeout: 10_000,
            responseType: 'json',
            data: requestFields,
        };

        try {
            const response = await axios.request(requestConfig);
            console.log(`The response from server : ${response}`);
            return response.data;

        } catch (error) {
            console.error('error: ', error);
            return null;
        }
    };

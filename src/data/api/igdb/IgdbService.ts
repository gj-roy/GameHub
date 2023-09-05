import axios, {AxiosRequestConfig} from "axios";
import {IGDB_API_BASE_URL} from "../../../secrets/Secrets";

export const IgdbService: (requestFields: string) => Promise<any> =
    async (requestFields: string) => {

        const requestConfig: AxiosRequestConfig = {
            url: '/games',
            method: 'POST',
            baseURL: IGDB_API_BASE_URL,
            headers: {
                'Client-ID': 'g88ty1lcsaf2ewvqqpyxngpssbvhsq',
                'Authorization': 'Bearer gdwbczt2cyhp5l2d0akrl9292feav9',
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

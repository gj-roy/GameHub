import axios, {AxiosRequestConfig} from "axios";
import {ApiGame} from "./entities/ApiGame";

export const IgdbService: (requestConfig: AxiosRequestConfig) => Promise<ApiGame[]> =
    async (requestConfig: AxiosRequestConfig) => {

        try {
            const response = await axios.request(requestConfig);
            return response.data;
        } catch (error) {
            console.error('error: ', error);
            return null;
        }
    };

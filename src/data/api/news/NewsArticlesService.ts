import {GAMESPOT_API_BASE_URL, GAMESPOT_API_KEY} from "../../../secrets/Secrets";
import axios, {AxiosResponse} from "axios";
import {ApiNewsResult} from "../../entities/news/NewsResult";

/**
 * For further information, read "https://www.gamespot.com/api/documentation#toc-0-4"
 * This API service is consuming ARTICLES from GameSpot API service.
 *
 * @property format ->  The data format of the response, takes either xml, json, or jsonp.
 *
 * @property field_list -> List of field names to include in the response. Use this if you want to
 * reduce the size of the response payload. This filter can accept multiple arguments,
 * each delimited with a ",".
 *
 * @property limit -> The number of results to display per page. This value defaults to 100 and
 * can not exceed this number.
 *
 * @property offset -> Return results starting with the object at the offset specified.
 *
 * @property sort -> The result set can be sorted by the marked fields in the Fields
 * section below. Format: &sort=field:direction where direction is either asc or desc.
 *
 * @property filter -> The result can be filtered by the marked fields in the Fields section below.
 * Single filter: &filter=field:value
 * Multiple filters: &filter=field:value,field:value
 * Date filters: &filter=field:start date|end date (using datetime format)
 * Associations: &association=guid
 *
 */
export type GameSpotNewsArticlesRequestParams = {
    format: "xml" | "json" | "jsonp";
    field_list: any;
    limit: number;
    offset: number;
    sort: any;
    filter: any;
};

/**
 * All the different kinds of API requests we can send via axios(it's generally applicable
 * to all kinds of APIs we consume).
 */
type ApiCallOptions = {
    method: string;
    url: string;
    params: GameSpotNewsArticlesRequestParams;
};

export const NewsArticlesService: (params: GameSpotNewsArticlesRequestParams) => Promise<ApiNewsResult | null> =
    async (params: GameSpotNewsArticlesRequestParams) => {

        const options: ApiCallOptions = {
            method: 'GET',
            url: `${GAMESPOT_API_BASE_URL}/articles/?api_key=${GAMESPOT_API_KEY}`,
            params: params,
        };

        try {
            const response = await axios.request<ApiNewsResult, AxiosResponse<ApiNewsResult>>(options);
            return response.data;
        } catch (error) {
            console.error('error: ', error);
            return null;
        }
    }


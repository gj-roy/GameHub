import {ApiNewsArticle} from "./ApiNewsArticle";

export type ApiNewsResult = {
    error: string;
    limit: number;
    offset: number;
    number_of_page_results: number;
    number_of_total_results: number;
    status_code: number;
    results: ApiNewsArticle[];
    version: string;
}

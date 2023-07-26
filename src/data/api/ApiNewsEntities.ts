import {ApiNewsArticle} from "../entities/NewsArticle";

/**
 * The main image of the news article.
 */
export type ApiNewsArticleImage = {
    square_tiny: string;
    screen_tiny: string;
    square_small: string;
    original: string;
}

export type ApiNewsCategory = {
    id: number;
    name: string;
}

export type ApiNewsAssociation = {
    id: number;
    name: string;
    api_detail_url: string;
    guid: string;
}

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

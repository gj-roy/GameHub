import {ApiNewsArticleImage, ApiNewsAssociation, ApiNewsCategory} from "../api/ApiNewsEntities";

/**
 * The news article that we receive from the web server.
 */
export type ApiNewsArticle = {
    publish_date: string;
    update_date: string;
    id: number;
    authors: string;
    title: string;
    image: ApiNewsArticleImage;
    deck: string;
    body: string;
    lede: string;
    categories: ApiNewsCategory[];
    associations: ApiNewsAssociation[];
    site_detail_url: string;
};

/**
 * This is the news article that we will be working with in our app.
 */
export type DomainNewsArticle = {
    id: number;
    title: string;
    lede:string;
    publish_date: string;
    site_detail_url: string;
    image: string;
};

import {DomainNewsArticle} from "../../../domain/news/DomainNewsArticle";
import {ApiNewsArticleImage} from "./ApiNewsArticleImage";
import {ApiNewsCategory} from "./ApiNewsCategory";
import {ApiNewsAssociation} from "./ApiNewsAssociation";


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
 * You give it an object of type "ApiNewsArticle" and it will
 * convert it to "DomainNewsArticle" and return it.
 */
export function convertApiNewsArticleToDomainNewsArticle(apiNewsArticle: ApiNewsArticle): DomainNewsArticle {
    return {
        id: apiNewsArticle?.id ?? -1,
        title: apiNewsArticle?.title ?? " ",
        lede: apiNewsArticle?.lede ?? "",
        publish_date: apiNewsArticle?.publish_date ?? "",
        site_detail_url: apiNewsArticle?.site_detail_url ?? "",
        image: apiNewsArticle?.image.original ?? ""
    };
}

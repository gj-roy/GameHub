import {GameSpotNewsArticlesRequestParams, NewsArticlesService} from "./NewsArticlesService";

export class RemoteNewsArticlesDataSource {

    constructor() {
    }

    getArticles() {

        const requestParams: GameSpotNewsArticlesRequestParams = {
            format: 'json',
            field_list: null,
            limit: 100,
            offset: 0,
            sort: "publish_date:desc",
            filter: "categories:18",
        }

        return NewsArticlesService(requestParams);

    }
}

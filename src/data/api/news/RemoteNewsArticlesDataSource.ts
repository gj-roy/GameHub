import {newsServerResponse} from "./FakeNewsServer";

export class RemoteNewsArticlesDataSource {

    constructor() {
    }

    getArticles() {
        return newsServerResponse;
    }
}

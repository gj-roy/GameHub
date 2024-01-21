import {newsServerResponse} from "./FakeNewsServer";
import {ApiNewsResult} from "./entities/ApiNewsResult";

export class RemoteNewsArticlesDataSource {

    constructor() {
    }

    getArticles(): ApiNewsResult {
        return newsServerResponse;
    }
}

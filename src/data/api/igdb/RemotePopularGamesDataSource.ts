import {IgdbService} from "./IgdbService";

export class RemotePopularGamesDataSource {

    constructor() {
    }

    getPopularGames() {

        return IgdbService('fields id,name,category,cover,first_release_date,game_modes,genres,involved_companies,platforms,player_perspectives,screenshots,summary,videos,websites,themes,url ;where rating != null;where first_release_date > 1538129354;limit 10;sort total_rating desc;');
    }
}

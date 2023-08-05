import {IgdbService} from "./IgdbService";

export class RemoteComingSoonGamesDataSource {

    constructor() {
    }

    getComingSoonGames() {

        return IgdbService('fields id,name,category,cover,first_release_date,game_modes,genres,involved_companies,platforms,player_perspectives,screenshots,summary,videos,websites,themes,url ;where first_release_date > 1691193575 ;limit 10;sort first_release_date asc;');
    }
}

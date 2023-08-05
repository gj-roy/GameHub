import {IgdbService} from "./IgdbService";

export class RemoteMostAnticipatedGamesDataSource {

    constructor() {
    }

    getMostAnticipatedGames() {
        return IgdbService('fields id,name,category,cover,first_release_date,game_modes,genres,involved_companies,platforms,player_perspectives,screenshots,summary,videos,websites,themes,url ;where first_release_date > 1691193575 & hypes != null ;limit 10;sort hypes desc;');
    }
}

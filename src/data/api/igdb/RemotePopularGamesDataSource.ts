import {IGDB_API_BASE_URL} from "../../../secrets/Secrets";


export class RemotePopularGamesDataSource {

    constructor() {
    }

    private getPopularGames(): Promise<any> | null {

        let response: Promise<any> | null = null
        fetch(
            `${IGDB_API_BASE_URL}/games`,
            {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Client-ID': 'g88ty1lcsaf2ewvqqpyxngpssbvhsq',
                    'Authorization': 'Bearer g92h6pqhvsmiczksjvvjd6weswhc5o',
                },
                body: 'fields *;where rating != null;where first_release_date > 1538129354;limit 50;sort total_rating desc;'
            }).then(result => {
            response = result.json();
        })
            .catch(error => {
                console.error(error);
                response = null;
            });

        return response;
    }


}

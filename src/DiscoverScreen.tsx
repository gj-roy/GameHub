import {ScrollView} from 'react-native'
import React, {useEffect, useState} from 'react'
import {GamesCategoryPreview} from "./ui/GamesCategoryPreview";
import {GameHubColors} from "./theme/GameHubTheme";
import {RemoteGamesDataSource} from "./data/api/igdb/RemoteGamesDataSource";
import {ApiGamePreview} from "./data/api/igdb/entities/ApiGamePreview";

// const oAuthCredentialsRepository = async () => {
//     const dataSource = new RemoteOAuthDataSource();
//     return dataSource.getCredentials();
// };

const popularGamesRepository = async () => {
    const dataSource = new RemoteGamesDataSource();
    return dataSource.getPopularGames();
};

const recentlyReleasedGamesRepository = async () => {
    const dataSource = new RemoteGamesDataSource();
    return dataSource.getRecentlyReleasedGames();
};

const comingSoonGamesRepository = async () => {
    const dataSource = new RemoteGamesDataSource();
    return dataSource.getComingSoonGames();
};

const mostAnticipatedGamesRepository = async () => {
    const dataSource = new RemoteGamesDataSource();
    return dataSource.getMostAnticipatedGames();
};

export const DiscoverScreen = () => {

    // const [oAuthCredentials, setOAuthCredentials] = useState<ApiOAuthResult | null>(null);
    const [popularGames, setPopularGames] = useState<ApiGamePreview[]>([]);
    const [recentlyReleasedGames, setRecentlyReleasedGames] = useState<ApiGamePreview[]>([]);
    const [comingSoonGames, setComingSoonGames] = useState<ApiGamePreview[]>([]);
    const [mostAnticipatedGames, setMostAnticipatedGames] = useState<ApiGamePreview[]>([]);

    useEffect(() => {
        // oAuthCredentialsRepository()
        //     .then(credentials => {
        //         setOAuthCredentials(credentials);
        //     })
        //     .catch((error) => {
        //         console.error(`Promise of credentials repository was rejected : ${error}`)
        //     });
        popularGamesRepository()
            .then(games => {
                setPopularGames(games);
            })
            .catch((error) => {
                console.error(`Promise of PopularGamesRepository was rejected : ${error}`);
                setPopularGames([]);
            });
        recentlyReleasedGamesRepository()
            .then(games => {
                setRecentlyReleasedGames(games);
            })
            .catch((error) => {
                console.error(`Promise of RecentlyReleasedGamesRepository was rejected : ${error}`);
                setRecentlyReleasedGames([]);
            });
        comingSoonGamesRepository()
            .then(games => {
                setComingSoonGames(games);
            })
            .catch((error) => {
                console.error(`Promise of ComingSoonGamesRepository was rejected : ${error}`);
                setComingSoonGames([]);
            });
        mostAnticipatedGamesRepository()
            .then(games => {
                setMostAnticipatedGames(games);
            })
            .catch((error) => {
                console.error(`Promise of MostAnticipatedGamesRepository was rejected : ${error}`);
                setMostAnticipatedGames([]);
            });
    }, []);

    // console.log(`The credentials were received 👇🏼`);
    // console.log(`${oAuthCredentials?.access_token} - ${oAuthCredentials?.token_type} - ${oAuthCredentials?.expires_in}`);

    return (
        <ScrollView style={{
            flex: 1,
            backgroundColor: GameHubColors.neutral
        }}>
            <GamesCategoryPreview title="popular" games={popularGames}/>
            <GamesCategoryPreview title="Recently Released" games={recentlyReleasedGames}/>
            <GamesCategoryPreview title="Coming Soon" games={comingSoonGames}/>
            <GamesCategoryPreview title="Most Anticipated" games={mostAnticipatedGames}/>

        </ScrollView>
    );
}

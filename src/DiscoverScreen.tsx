import {ScrollView} from 'react-native'
import React, {useEffect, useState} from 'react'
import {GamesCategoryPreview} from "./ui/GamesCategoryPreview";
import {GameHubColors} from "./theme/GameHubTheme";
import {RemoteGamesDataSource} from "./data/api/igdb/RemoteGamesDataSource";
import {ApiGame} from "./data/api/igdb/entities/ApiGame";
import {convertApiGameToPreviewHeaderGame} from "./data/mappers/ApiGameMappers";

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
    const [popularGames, setPopularGames] = useState<ApiGame[] | null>([]);
    const [recentlyReleasedGames, setRecentlyReleasedGames] = useState<ApiGame[] | null>([]);
    const [comingSoonGames, setComingSoonGames] = useState<ApiGame[] | null>([]);
    const [mostAnticipatedGames, setMostAnticipatedGames] = useState<ApiGame[] | null>([]);

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
            });
        recentlyReleasedGamesRepository()
            .then(games => {
                setRecentlyReleasedGames(games);
            })
            .catch((error) => {
                console.error(`Promise of RecentlyReleasedGamesRepository was rejected : ${error}`);
            });
        comingSoonGamesRepository()
            .then(games => {
                setComingSoonGames(games);
            })
            .catch((error) => {
                console.error(`Promise of ComingSoonGamesRepository was rejected : ${error}`);
            });
        mostAnticipatedGamesRepository()
            .then(games => {
                setMostAnticipatedGames(games);
            })
            .catch((error) => {
                console.error(`Promise of MostAnticipatedGamesRepository was rejected : ${error}`);
            });
    }, []);

    // console.log(`The credentials were received 👇🏼`);
    // console.log(`${oAuthCredentials?.access_token} - ${oAuthCredentials?.token_type} - ${oAuthCredentials?.expires_in}`);

    return (
        <ScrollView style={{
            flex: 1,
            backgroundColor: GameHubColors.neutral
        }}>
            <GamesCategoryPreview title="popular" games={
                popularGames?.map(convertApiGameToPreviewHeaderGame) ?? []
            }/>
            <GamesCategoryPreview title="Recently Released" games={
                recentlyReleasedGames?.map(convertApiGameToPreviewHeaderGame) ?? []
            }/>
            <GamesCategoryPreview title="Coming Soon" games={
                comingSoonGames?.map(convertApiGameToPreviewHeaderGame) ?? []
            }/>
            <GamesCategoryPreview title="Most Anticipated" games={
                mostAnticipatedGames?.map(convertApiGameToPreviewHeaderGame) ?? []
            }/>

        </ScrollView>
    );
}

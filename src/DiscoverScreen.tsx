import {ScrollView} from 'react-native'
import React, {useEffect, useState} from 'react'
import {GamesCategoryPreview, GamesCategoryPreviewDataModel} from "./ui/GamesCategoryPreview";
import {GameHubColors} from "./theme/GameHubTheme";
import {RemotePopularGamesDataSource} from "./data/api/igdb/RemotePopularGamesDataSource";
import {RemoteRecentlyReleasedGamesDataSource} from "./data/api/igdb/RemoteRecentlyReleasedGamesDataSource";
import {RemoteComingSoonGamesDataSource} from "./data/api/igdb/RemoteComingSoonGamesDataSource";
import {RemoteMostAnticipatedGamesDataSource} from "./data/api/igdb/RemoteMostAnticipatedGamesDataSource";
import {ApiGame} from "./data/api/igdb/entities/ApiGame";

// const oAuthCredentialsRepository = async () => {
//     const dataSource = new RemoteOAuthDataSource();
//     return dataSource.getCredentials();
// };

const popularGamesRepository = async () => {
    const dataSource = new RemotePopularGamesDataSource();
    return dataSource.getPopularGames();
};

const recentlyReleasedGamesRepository = async () => {
    const dataSource = new RemoteRecentlyReleasedGamesDataSource();
    return dataSource.getRecentlyReleasedGames();
};

const comingSoonGamesRepository = async () => {
    const dataSource = new RemoteComingSoonGamesDataSource();
    return dataSource.getComingSoonGames();
};

const mostAnticipatedGamesRepository = async () => {
    const dataSource = new RemoteMostAnticipatedGamesDataSource();
    return dataSource.getMostAnticipatedGames();
};

const convertApiGameToPreviewHeaderGame = (game: ApiGame) => {

    const resultingGame: GamesCategoryPreviewDataModel = {
        id: game.id,
        title: game.name,
        coverUrl: `https://images.igdb.com/igdb/image/upload/t_cover_big/${game.cover?.image_id}.jpg`
    };
    return resultingGame;
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

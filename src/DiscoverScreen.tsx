import {ScrollView} from 'react-native'
import React, {useEffect, useState} from 'react'
import {GamesCategoryPreview, GamesCategoryPreviewDataModel} from "./ui/GamesCategoryPreview";
import {GameHubColors} from "./theme/GameHubTheme";
import {RemoteOAuthDataSource} from "./data/api/auth/RemoteOAuthDataSource";
import {RemotePopularGamesDataSource} from "./data/api/igdb/RemotePopularGamesDataSource";
import {RemoteRecentlyReleasedGamesDataSource} from "./data/api/igdb/RemoteRecentlyReleasedGamesDataSource";
import {RemoteComingSoonGamesDataSource} from "./data/api/igdb/RemoteComingSoonGamesDataSource";
import {RemoteMostAnticipatedGamesDataSource} from "./data/api/igdb/RemoteMostAnticipatedGamesDataSource";
import {ApiGame} from "./data/api/igdb/entities/ApiGame";
import {ApiOAuthResult} from "./data/api/auth/entities/ApiOAuthResult";

const oAuthCredentialsRepository = async () => {
    const dataSource = new RemoteOAuthDataSource();
    return dataSource.getCredentials();
};

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

const convertApiGameToPreviewHeaderGame = (game: ApiGame, index: number) => {

    const resultingGame: GamesCategoryPreviewDataModel = {
        id: index,
        title: game.name,
        coverUrl: `https://images.igdb.com/igdb/image/upload/t_cover_big/${game.cover?.image_id}.jpg`
    };
    return resultingGame;
};

export const DiscoverScreen = () => {

    const [oAuthCredentials, setOAuthCredentials] = useState<ApiOAuthResult | null>(null);
    const [popularGames, setPopularGames] = useState<ApiGame[]>([]);
    const [recentlyReleasedGames, setRecentlyReleasedGames] = useState<ApiGame[]>([]);
    const [comingSoonGames, setComingSoonGames] = useState<ApiGame[]>([]);
    const [mostAnticipatedGames, setMostAnticipatedGames] = useState<ApiGame[]>([]);

    useEffect(() => {
        oAuthCredentialsRepository().then(credentials => {
            setOAuthCredentials(credentials);
        });
        popularGamesRepository().then(games => {
            setPopularGames(games);
        });
        recentlyReleasedGamesRepository().then(games => {
            setRecentlyReleasedGames(games);
        });
        comingSoonGamesRepository().then(games => {
            setComingSoonGames(games);
        });
        mostAnticipatedGamesRepository().then(games => {
            setMostAnticipatedGames(games);
        });
    }, []);

    console.log(`The credentials were received 👇🏼`);
    console.log(`${oAuthCredentials?.access_token} - ${oAuthCredentials?.token_type} - ${oAuthCredentials?.expires_in}`);

    return (
        <ScrollView style={{
            flex: 1,
            backgroundColor: GameHubColors.neutral
        }}>
            <GamesCategoryPreview title="popular" games={
                popularGames.map(convertApiGameToPreviewHeaderGame)
            }/>
            <GamesCategoryPreview title="Recently Released" games={
                recentlyReleasedGames.map(convertApiGameToPreviewHeaderGame)
            }/>
            <GamesCategoryPreview title="Coming Soon" games={
                comingSoonGames.map(convertApiGameToPreviewHeaderGame)
            }/>
            <GamesCategoryPreview title="Most Anticipated" games={
                mostAnticipatedGames.map(convertApiGameToPreviewHeaderGame)
            }/>

        </ScrollView>
    );
}

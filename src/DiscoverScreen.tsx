import {ScrollView} from 'react-native'
import React, {useEffect} from 'react'
import {GamesCategoryPreview, GamesCategoryPreviewDataModel} from "./ui/GamesCategoryPreview";
import {GameHubColors} from "./theme/GameHubTheme";
import {RemoteOAuthDataSource} from "./data/api/auth/RemoteOAuthDataSource";
import {RemotePopularGamesDataSource} from "./data/api/igdb/RemotePopularGamesDataSource";
import {RemoteRecentlyReleasedGamesDataSource} from "./data/api/igdb/RemoteRecentlyReleasedGamesDataSource";
import {RemoteComingSoonGamesDataSource} from "./data/api/igdb/RemoteComingSoonGamesDataSource";
import {RemoteMostAnticipatedGamesDataSource} from "./data/api/igdb/RemoteMostAnticipatedGamesDataSource";

export const DiscoverScreen = () => {

    const getRemoteOAuthCredentials = async () => {
        const dataSource = new RemoteOAuthDataSource();
        return dataSource.getCredentials();
    };

    const getRemotePopularGames = async () => {
        const dataSource = new RemotePopularGamesDataSource();
        return dataSource.getPopularGames();
    };

    const getRemoteRecentlyReleasedGames = async () => {
        const dataSource = new RemoteRecentlyReleasedGamesDataSource();
        return dataSource.getRecentlyReleasedGames();
    };

    const getRemoteComingSoonGames = async () => {
        const dataSource = new RemoteComingSoonGamesDataSource();
        return dataSource.getComingSoonGames();
    };

    const getRemoteMostAnticipatedGames = async () => {
        const dataSource = new RemoteMostAnticipatedGamesDataSource();
        return dataSource.getMostAnticipatedGames();
    };


    useEffect(() => {

        // getRemoteOAuthCredentials().then(credentials => {
        //
        //     console.log(`The acquired credentials are: ${credentials.access_token}`);
        //     console.log(`The acquired credentials are: ${credentials.expires_in}`);
        //     console.log(`The acquired credentials are: ${credentials.token_type}`);
        // });

        getRemoteMostAnticipatedGames().then(games => {
            games.map((popularGame, index) => {
                console.log(`${index} - ${popularGame.url}`);
            })
        })

    }, []);

    const fakeGames: GamesCategoryPreviewDataModel[] = [
        {id: 1, title: "Super Catboy", coverUrl: "https://images.igdb.com/igdb/image/upload/t_cover_big/co42mw.jpg"},
        {
            id: 2,
            title: "The Legend of Zelda: Tears of the Kingdom",
            coverUrl: "https://images.igdb.com/igdb/image/upload/t_cover_big/co5vmg.jpg"
        },
        {
            id: 3,
            title: "Street Fighter 6",
            coverUrl: "https://images.igdb.com/igdb/image/upload/t_cover_big/co5vst.jpg"
        },
        {
            id: 4,
            title: "The Banished Vault",
            coverUrl: "https://images.igdb.com/igdb/image/upload/t_cover_big/co69qx.jpg"
        },
    ];

    return (
        <ScrollView style={{
            flex: 1,
            backgroundColor: GameHubColors.neutral
        }}>
            <GamesCategoryPreview title="popular" games={fakeGames}/>
            <GamesCategoryPreview title="Recently Released" games={fakeGames}/>
            <GamesCategoryPreview title="Coming Soon" games={fakeGames}/>
            <GamesCategoryPreview title="Most Anticipated" games={fakeGames}/>

        </ScrollView>
    )
}

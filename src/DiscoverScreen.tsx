import {ScrollView} from 'react-native'
import React, {useEffect} from 'react'
import {GamesCategoryPreview, GamesCategoryPreviewDataModel} from "./ui/GamesCategoryPreview";
import {GameHubColors} from "./theme/GameHubTheme";
import {RemoteOAuthDataSource} from "./data/api/auth/RemoteOAuthDataSource";

export const DiscoverScreen = () => {

    const getRemoteOAuthCredentials = async () => {
        const dataSource = new RemoteOAuthDataSource();
        return dataSource.getCredentials();
    }

    useEffect(() => {

        getRemoteOAuthCredentials().then(credentials => {

            console.log(`The acquired credentials are: ${credentials.access_token}`);
            console.log(`The acquired credentials are: ${credentials.expires_in}`);
            console.log(`The acquired credentials are: ${credentials.token_type}`);
        });
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

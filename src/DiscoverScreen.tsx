import {View} from 'react-native'
import React from 'react'
import {GamesCategoryPreview, GamesCategoryPreviewDataModel} from "./ui/GamesCategoryPreview";

export const DiscoverScreen = () => {

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
        <View style={{
            flex: 1,
        }}>
            <GamesCategoryPreview title="popular" games={fakeGames}/>
        </View>
    )
}

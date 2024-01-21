import {ScrollView} from 'react-native'
import React, {useEffect, useState} from 'react'
import {GamesCategoryPreview} from "./ui/GamesCategoryPreview";
import {GameHubColors} from "./theme/GameHubTheme";
import {RemoteGamePreviewsDataSource} from "./data/api/igdb/RemoteGamePreviewsDataSource";
import {ApiGamePreview} from "./data/api/igdb/entities/ApiGamePreview";

const getRemoteGamePreviews = () => {
    const dataSource = new RemoteGamePreviewsDataSource();
    return dataSource.getFakeGamePreviews();
}

export const DiscoverScreen = () => {

    const [gamePreviews, setGamePreviews] = useState<ApiGamePreview[]>([]);

    useEffect(() => {
        setGamePreviews(getRemoteGamePreviews());
    }, []);

    return (
        <ScrollView style={{
            flex: 1,
            backgroundColor: GameHubColors.neutral
        }}>
            <GamesCategoryPreview title="popular" games={gamePreviews}/>
            <GamesCategoryPreview title="Recently Released" games={gamePreviews}/>
            <GamesCategoryPreview title="Coming Soon" games={gamePreviews}/>
            <GamesCategoryPreview title="Most Anticipated" games={gamePreviews}/>

        </ScrollView>
    );
}

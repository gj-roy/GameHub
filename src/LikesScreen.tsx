import {FlatList, Text, View} from 'react-native'
import React, {useEffect, useState} from 'react'
import {GetLikedGamesUseCase} from "./feature_likes/UseCases";

export const LikesScreen = () => {

    const [likedGames, setLikedGames] = useState<string[]>([])

    useEffect(() => {
        GetLikedGamesUseCase()
            .then((result) => {
                setLikedGames(result);
            });
    }, []);

    // Render each item in the list
    // @ts-ignore
    const renderItem = ({item}) => (
        <View>
            <Text>{item}</Text>
        </View>
    );

    return (
        <View style={{
            flex: 1,
            justifyContent: 'center',
            alignItems: 'center'
        }}>
            <Text>LikesScreen</Text>
            <FlatList
                data={likedGames}
                renderItem={renderItem}
                keyExtractor={(item, index) => index.toString()}
            />
        </View>
    )
}

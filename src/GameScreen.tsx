import * as React from 'react';
import {useEffect, useState} from 'react';

import {Text, View} from 'react-native';
import {useRoute} from "@react-navigation/native";
import {RemoteGameDataSource} from "./data/api/igdb/RemoteGameDataSource";
import {ApiGame} from "./data/api/igdb/entities/ApiGame";

const gameRepository = async (id: number) => {
    const dataSource = new RemoteGameDataSource();
    return dataSource.getSpecificGameDetails(id);
};


/**
 * The page for showing detailed information about a single game.
 * User usually comes to this screen after clicking on a game in
 * any of the other pages.
 */
export const GameScreen = () => {


    // the data that was given to this route
    // @ts-ignore
    const {itemId} = useRoute().params;

    const [game, setGame] = useState<ApiGame | null>(null);
    useEffect(() => {
        gameRepository(itemId as number)
            .then(listOfGames => {
                setGame(listOfGames[0]);
            })
            .catch((error) => {
                console.error(`Promise of GameRepository was rejected : ${error}`);
            });
    }, []);

    return (
        <View
            style={{
                marginTop: 35,
                flex: 1,
                alignItems: 'center'
            }}
        >

            <Text
                style={{
                    fontWeight: 'bold',
                    marginTop: 14,
                    fontSize: 20,
                }}
            >
                {game?.name}
            </Text>


        </View>
    );
}

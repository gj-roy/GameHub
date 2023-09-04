import * as React from 'react';
import {useEffect, useState} from 'react';

import {Image, Text, TouchableOpacity, View} from 'react-native';
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

    const [isLiked, setIsLiked] = useState(false);
    const [game, setGame] = useState<ApiGame | null>(null);
    useEffect(() => {
        console.log(`Game's ID is : ${itemId}`);
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
                flexDirection: 'column',
            }}
        >
            <Image
                style={{
                    width: '100%',
                    height: 160,
                    resizeMode: 'cover'
                }}
                source={{uri: `https://images.igdb.com/igdb/image/upload/t_cover_small/${game?.cover?.image_id}.jpg`}}/>

            <View
                style={{
                    position: 'absolute',
                    marginTop: 120,
                    flexDirection: 'column',
                    flex: 1,
                }}
            >

                <View
                    style={{
                        flexDirection: 'row',
                        width: '100%',
                        left: 15,
                    }}
                >
                    <Image style={{
                        width: 120,
                        height: 140,
                        borderRadius: 4
                    }}
                           source={{uri: `https://images.igdb.com/igdb/image/upload/t_cover_small/${game?.cover?.image_id}.jpg`}}
                    />

                    <View
                        style={{
                            flexDirection: 'column',
                            marginLeft: 8,
                            marginTop: 58,
                        }}
                    >
                        <Text
                            style={{
                                fontWeight: '700',
                                fontSize: 17,
                                lineHeight: 20,
                                letterSpacing: 0.3,
                            }}
                        >
                            {(game?.name.length ?? 0) <= 22 ? game?.name : `${game?.name.substring(0, 22)}...`}
                        </Text>
                        <Text
                            style={{
                                marginTop: 5,
                            }}
                        >
                            Feb 09, 2023 (a month ago)
                        </Text>
                        <Text>
                            Avalanche Software
                        </Text>
                    </View>

                    <TouchableOpacity
                        style={{
                            right: 10,
                            marginTop: 15,
                        }}
                        onPress={() => {
                            setIsLiked(!isLiked);
                        }}
                    >
                        <Text
                            style={{
                                fontSize: 23
                            }}
                        >{isLiked ? "❤️" : "🤍"}</Text>
                    </TouchableOpacity>


                </View>


            </View>


        </View>
    );
}

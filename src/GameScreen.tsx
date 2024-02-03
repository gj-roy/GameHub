import * as React from 'react';
import {useEffect, useState} from 'react';

import {Image, Text, TouchableOpacity, View} from 'react-native';
import {useRoute} from "@react-navigation/native";
import {ApiGame} from "./data/api/igdb/entities/ApiGame";
import {RemoteGameDataSource} from "./data/api/igdb/RemoteGameDataSource";
import {AddGameToLikedListUseCase, RemoveGameFromLikedListUseCase} from './feature_likes/UseCases';
import AsyncStorage from "@react-native-async-storage/async-storage";
import {LIKED_GAMES_DB_KEY} from "./feature_likes/Constants";


const getSingleGameData = async (id: number) => {
    const dataSource = new RemoteGameDataSource();
    return dataSource.getFakeGameData(id);
};

function convertUnixTimeStampToPrettyDate(unixTime: number): string {
    const prettyDate = new Date(unixTime * 1000);

    //get the month of the year
    const monthNumber = prettyDate.getMonth();
    let monthName: string;
    switch (monthNumber) {
        case 0:
            monthName = "Jan";
            break;
        case 1:
            monthName = "Feb";
            break;
        case 2:
            monthName = "Mar";
            break;
        case 3:
            monthName = "Apr";
            break;
        case 4:
            monthName = "May";
            break;
        case 5:
            monthName = "Jun";
            break;
        case 6:
            monthName = "Jul";
            break;
        case 7:
            monthName = "Aug";
            break;
        case 8:
            monthName = "Sep";
            break;
        case 9:
            monthName = "Oct";
            break;
        case 10:
            monthName = "Nov";
            break;
        case 11:
            monthName = "Dec";
            break;
        default:
            monthName = "";
            break;
    }

    // get day of the month
    const dayNumber = prettyDate.getDate();
    // get the year
    const year = prettyDate.getFullYear();

    return `${monthName} ${dayNumber}, ${year}`;
}

/**
 * The page for showing detailed information about a single game.
 * User usually comes to this screen after clicking on a game in
 * any of the other pages.
 */
export const GameScreen = () => {

    const [isLiked, setIsLiked] = useState<boolean | null>(null);
    const [game, setGame] = useState<ApiGame | null>(null);

    // the data that was given to this route
    // @ts-ignore
    const {itemId} = useRoute().params;


    useEffect(() => {

        /**
         * Check if this game is liked by the user.
         *
         * @param {string} gameId - The ID of the game to check.
         */
        const CheckIfGameIsLikedUseCase = async (gameId: string) => {
            // Firstly, get liked games from database (as a string)
            const likedGamesString: string | null = await AsyncStorage.getItem(LIKED_GAMES_DB_KEY);
            // This string should be converted to an array (if the string was null, we'll just make an empty array).
            const likedGamesArray: string[] = (likedGamesString?.split(',')) ?? [];

            setIsLiked(likedGamesArray.includes(gameId));
        }

        CheckIfGameIsLikedUseCase(itemId.toString())

    }, []);


    useEffect(() => {
        console.log(`Game's ID is : ${itemId}`);
        getSingleGameData(itemId as number)
            .then(gameData => {
                setGame(gameData);
            })
            .catch((error) => {
                console.error(`Promise of GameRepository was rejected : ${error}`);
                throw error;
            });
    }, []);

    useEffect(() => {
        if (isLiked) {
            // user has liked the game
            AddGameToLikedListUseCase(itemId.toString())
                .then(result => {
                    if (!result) {
                        console.error("There was an error saving the game state into database.");
                    } else {
                        console.log("The gameId was added successfully.");
                    }
                })
                .catch(error => {
                    console.error(`Promise of setGameLikedUseCase was rejected : ${error}`);
                    throw error;
                });
        } else {
            // User has un-liked the game
            RemoveGameFromLikedListUseCase(itemId.toString())
                .then(() => {
                    console.log("The gameId was removed successfully.");
                })
                .catch(error => {
                    console.error(`There was an error in removeGameLikedUseCase : ${error}.`);
                    throw error;
                });
        }
    }, [isLiked]);

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
                source={{uri: `https://images.igdb.com/igdb/image/upload/t_cover_big/${game?.cover?.image_id}.jpg`}}/>

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
                           source={{uri: `https://images.igdb.com/igdb/image/upload/t_cover_big/${game?.cover?.image_id}.jpg`}}
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
                            {convertUnixTimeStampToPrettyDate(game?.first_release_date ?? 0)}
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



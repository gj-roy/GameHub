import * as React from 'react';
import {useEffect, useState} from 'react';

import {Image, Text, TouchableOpacity, View} from 'react-native';
import {useRoute} from "@react-navigation/native";
import {ApiGame} from "./data/api/igdb/entities/ApiGame";
import {RemoteGameDataSource} from "./data/api/igdb/RemoteGameDataSource";

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


    // the data that was given to this route
    // @ts-ignore
    const {itemId} = useRoute().params;

    const [isLiked, setIsLiked] = useState(false);
    const [game, setGame] = useState<ApiGame | null>(null);
    useEffect(() => {
        console.log(`Game's ID is : ${itemId}`);
        getSingleGameData(itemId as number)
            .then(gameData => {
                setGame(gameData);
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

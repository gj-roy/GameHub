import * as React from 'react';

import {Image, Text, TouchableOpacity, View} from 'react-native';
import {RouteProp, useNavigation, useRoute} from "@react-navigation/native";
import {GamesCategoryPreviewDataModel} from "./ui/GamesCategoryPreview";
import {ImageURISource} from "react-native/Libraries/Image/ImageSource";

/**
 * The page for showing detailed information about a single game.
 * User usually comes to this screen after clicking on a game in
 * any of the other pages.
 */
export const GameScreen = () => {

    const navigation = useNavigation();
    // the data that was given to this route
    const game = useRoute<RouteProp<Record<string, GamesCategoryPreviewDataModel>>>().params;
    const gameCoverUrl: ImageURISource = {uri: game.coverUrl ?? undefined}

    return (
        <View
            style={{
                flex: 1,
                alignItems: 'center'
            }}
        >

            <Image
                source={gameCoverUrl}
                style={{
                    width: '100%',
                    height: 240,
                    resizeMode: 'cover'
                }}
            />
            <Text
                style={{
                    fontWeight: 'bold',
                    marginTop: 14,
                    fontSize: 20,
                }}
            >
                {game.title}
            </Text>

            <TouchableOpacity
                style={{marginTop: 20}}
                onPress={() => {
                    console.log("user wants to go back home!!!!");
                    navigation.goBack();
                }}
            >
                <Text>Go back to HomeScreen</Text>
            </TouchableOpacity>
        </View>
    );
}

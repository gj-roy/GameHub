import * as React from 'react';

import {Text, TouchableOpacity, View} from 'react-native';
import {useNavigation} from "@react-navigation/native";

/**
 * The page for showing detailed information about a single game.
 * User usually comes to this screen after clicking on a game in
 * any of the other pages.
 */
export const GameScreen = () => {

    const navigation = useNavigation();

    return (
        <View
            style={{
                flex: 1,
                justifyContent: 'center',
                alignItems: 'center'
            }}
        >
            <Text
                style={{fontWeight: 'bold'}}
            >
                This is the game screen; it shows data about the game you clicked on.
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

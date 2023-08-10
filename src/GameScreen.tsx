import * as React from 'react';

import {Text, View} from 'react-native';

export const GameScreen = () => {


    return (
        <View
            style={{
                flex: 1,
                justifyContent: 'center',
                alignItems: 'center'
            }}
        >
            <Text>This is the game screen; it shows data about the game you clicked on.</Text>
        </View>
    );
}

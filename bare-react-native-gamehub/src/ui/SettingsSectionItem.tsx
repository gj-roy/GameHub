import React from 'react';
import {Text, View} from "react-native";

type SettingsSectionItemProps = {
    title: string;
    description: string;
};


export const SettingsSectionItem = ({title, description}: SettingsSectionItemProps) => {
    return (
        <View
            style={{
                flexDirection: 'column',
                paddingTop: 16,
            }}
        >
            <Text
                style={{
                    color: 'black',
                    fontWeight: 'bold',
                }}
            >{title}</Text>
            <Text
                style={{
                    fontWeight: '500',
                    paddingTop: 8,
                }}
            >{description}</Text>
        </View>
    );
}

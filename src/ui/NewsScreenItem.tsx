import React from 'react';
import {View, Image, ImageURISource, Text} from 'react-native';
import {GameHubColors} from "../theme/GameHubTheme";
import {ClockIcon} from "react-native-heroicons/outline";

export type NewsItemDataModel = {
    id: number;
    imageUrl: string;
    title: string;
    lede: string;
    publicationDate: string;
};

export const NewsScreenItem = ({id, imageUrl, title, lede, publicationDate}: NewsItemDataModel) => {

    const newsItemCoverUrl: ImageURISource = {
        uri: imageUrl
    }
    return (
        <View
            style={{
                flexDirection: 'column',
                alignItems: 'center',
                paddingVertical: 8,
                paddingHorizontal: 12,
            }}>
            <Image
                style={{
                    width: '100%',
                    height: 200,
                    borderRadius: 5,
                }}
                source={newsItemCoverUrl}
            />
            <Text style={{
                width: '100%',
                fontSize: 18,
                fontWeight: '500',
                color: 'black',
                marginTop: 5,
                textAlign: 'justify',
            }}>{title}</Text>
            <Text
                style={{
                    width: '100%',
                    fontSize: 16,
                    fontWeight: '400',
                    color: GameHubColors.primary,
                    marginTop: 7,
                    textAlign: 'justify',
                }}
            >
                {lede}
            </Text>
            <View
                style={{
                    marginTop: 5,
                    width: '100%',
                    flexDirection: 'row',
                    justifyContent: 'flex-start',
                    alignItems: 'center'
                }}
            >
                <ClockIcon size={16} color={GameHubColors.primary}/>
                <Text style={{
                    marginLeft: 4,
                    fontWeight: '600'
                }}>{publicationDate}</Text>
            </View>
        </View>
    );
}


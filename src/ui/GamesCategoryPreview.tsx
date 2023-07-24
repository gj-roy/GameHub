import {View, Text, TouchableOpacity, FlatList} from 'react-native'
import React from 'react'
import {GameHubColors} from "../theme/GameHubTheme";
import {GamePreviewCard} from "./GamePreviewCard";

/**
 * Everything that will be fed to {GamesCategoryPreview}.
 */
export type GamesCategoryPreviewProps = {
    title: string;
    games: GamesCategoryPreviewDataModel[];
};

/**
 * Each game you provide to the {GamesCategoryPreview} should be of
 * this type.
 */
export type GamesCategoryPreviewDataModel = {
    id: number;
    title: string;
    coverUrl: string | null;
};


/**
 * A view, with a title and an occasional "see all" button.
 * And a row of games previews below them.
 */
export const GamesCategoryPreview = ({title, games}: GamesCategoryPreviewProps) => {
    return (
        <View style={{
            flexDirection: 'column',
            paddingVertical: 8,
            paddingHorizontal: 12,
            backgroundColor: 'lightgray'
        }}>
            <View style={{
                flexDirection: 'row',
                justifyContent: 'space-between',
                alignItems: 'center',

            }}>
                <Text style={{
                    fontWeight: '500',
                    fontSize: 19,
                    color: 'black',
                    letterSpacing: 0.4
                }}>{title}</Text>

                <TouchableOpacity>
                    <Text style={{
                        color: GameHubColors.secondary,
                        fontSize: 14,
                        fontWeight: '600'
                    }}>
                        SEE ALL
                    </Text>
                </TouchableOpacity>
            </View>
            <FlatList
                style={{
                    paddingTop: 10,
                }}
                showsHorizontalScrollIndicator={false}
                horizontal={true}
                data={games}
                renderItem={({item}) => <GamePreviewCard game={item}/>}
                keyExtractor={item => item.id.toString()}
            />
        </View>
    )
}

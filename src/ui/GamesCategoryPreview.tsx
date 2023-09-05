import {FlatList, Text, TouchableOpacity, View} from 'react-native'
import React from 'react'
import {GameHubColors} from "../theme/GameHubTheme";
import {GamePreviewCard} from "./GamePreviewCard";
import {ApiGamePreview} from "../data/api/igdb/entities/ApiGamePreview";

/**
 * Everything that will be fed to {GamesCategoryPreview}.
 */
type GamesCategoryPreviewProps = {
    title: string;
    games: ApiGamePreview[];
};

/**
 * A view, with a title and an occasional "see all" button.
 * And a row of games previews below them.
 */
export const GamesCategoryPreview = ({title, games}: GamesCategoryPreviewProps) => {
    return (
        <View style={{
            flexDirection: 'column',
            paddingVertical: 10,
            paddingHorizontal: 12,
            marginBottom: 10,
            backgroundColor: 'white'
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

import React from 'react';
import {Image, TouchableOpacity} from "react-native";
import {GamesCategoryPreviewDataModel} from "./GamesCategoryPreview";
import {ImageURISource} from "react-native/Libraries/Image/ImageSource";
import {useNavigation} from "@react-navigation/native";
import {Destination} from "../navigation/Destination";
import {myStackNavigator} from "../navigation/Navigators";


type GamePreviewCardProps = {
    game: GamesCategoryPreviewDataModel;
};

/**
 * A card in the categorized list of games, which gives some info about the game.
 * And if clicked, leads the user to game's specific page.
 */
export const GamePreviewCard = ({game}: GamePreviewCardProps) => {

    // @ts-ignore
    const navigation = useNavigation().getParent(myStackNavigator.id);
    const gameCoverUrl: ImageURISource = {uri: game.coverUrl ?? undefined}

    return (
        <TouchableOpacity onPress={() => {
            // Goes to the GameScreen.
            navigation?.navigate(Destination.Game.route, {...game});
        }}>
            <Image
                style={{
                    width: 130,
                    height: 160,
                    borderRadius: 8,
                    marginRight: 5,
                    resizeMode: 'cover'
                }}
                source={gameCoverUrl}
            />
        </TouchableOpacity>
    );
}

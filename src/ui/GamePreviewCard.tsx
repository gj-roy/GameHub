import React from 'react';
import {Image} from "react-native";
import {GamesCategoryPreviewDataModel} from "./GamesCategoryPreview";
import {ImageURISource} from "react-native/Libraries/Image/ImageSource";


type GamePreviewCardProps = {
    game: GamesCategoryPreviewDataModel;

};

/**
 * A card in the categorized list of games, which gives some info about the game.
 * And if clicked, leads the user to game's specific page.
 */
export const GamePreviewCard = ({game}: GamePreviewCardProps) => {

    const gameCoverUrl: ImageURISource = {uri: game.coverUrl ?? undefined}
    return (
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
    );
}

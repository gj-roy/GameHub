import React from 'react';
import {Image, TouchableOpacity} from "react-native";
import {ImageURISource} from "react-native/Libraries/Image/ImageSource";
import {useNavigation} from "@react-navigation/native";
import {Destination} from "../navigation/Destination";
import {ApiGamePreview} from "../data/api/igdb/entities/ApiGamePreview";


type GamePreviewCardProps = {
    game: ApiGamePreview;
};

/**
 * A card in the categorized list of games, which gives some info about the game.
 * And if clicked, leads the user to game's specific page.
 */
export const GamePreviewCard = ({game}: GamePreviewCardProps) => {

    // @ts-ignore
    const navigation = useNavigation();
    const gameCoverUrl: ImageURISource = {uri: `https://images.igdb.com/igdb/image/upload/t_cover_big/${game.cover?.image_id}.jpg` ?? undefined}

    return (
        <TouchableOpacity onPress={() => {
            // Goes to the GameScreen.
            // @ts-ignore
            navigation?.navigate(Destination.Game.route, {
                itemId: game.id
            });
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

import {ApiGame} from "../api/igdb/entities/ApiGame";
import {GamesCategoryPreviewDataModel} from "../../ui/GamesCategoryPreview";


export function convertApiGameToPreviewHeaderGame(game: ApiGame): GamesCategoryPreviewDataModel {

    return {
        id: game.id,
        title: game.name,
        coverUrl: `https://images.igdb.com/igdb/image/upload/t_cover_big/${game.cover?.image_id}.jpg`
    };
}

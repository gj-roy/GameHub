import {ApiGame} from "../api/igdb/entities/ApiGame";
import {ApiGamePreview} from "../api/igdb/entities/ApiGamePreview";


export function convertApiGameToPreviewHeaderGame(game: ApiGame): ApiGamePreview {

    return {
        id: game.id,
        title: game.name,
        coverUrl: `https://images.igdb.com/igdb/image/upload/t_cover_big/${game.cover?.image_id}.jpg`
    };
}

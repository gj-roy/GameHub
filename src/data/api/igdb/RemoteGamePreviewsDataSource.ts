import {ApiGamePreview} from "./entities/ApiGamePreview";
import {gamePreviewsServerResponse} from "./FakeGamesServer";

/**
 * This data source is for getting preview information for lots of games.
 * At the time of writing, this DS is only used in DiscoverScreen.
 */
export class RemoteGamePreviewsDataSource {

    constructor() {
    }

    getFakeGamePreviews(): ApiGamePreview[] {
        return gamePreviewsServerResponse;
    }
}

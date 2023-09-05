import {ApiImage} from "./ApiImage";

/**
 * When we are in the DiscoverScreen (or any other screen that just shows previews of
 * various games and not detailed data about a game), we don't have to work with ApiGame,
 * we just work with this smaller version that contains less data but works just perfect
 * for showing a preview of lots of games to the user.
 */
export type ApiGamePreview = {
    id: number;
    name: string;
    cover: ApiImage | null;
};

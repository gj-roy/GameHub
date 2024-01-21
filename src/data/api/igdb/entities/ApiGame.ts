import {ApiWebsite} from "./ApiWebsite";
import {ApiInvolvedCompany} from "./ApiInvolvedCompany";
import {ApiMode} from "./ApiMode";
import {ApiTheme} from "./ApiTheme";
import {ApiPlayerPerspective} from "./ApiPlayerPerspective";
import {ApiPlatform} from "./ApiPlatform";
import {ApiGenre} from "./ApiGenre";
import {ApiVideo} from "./ApiVideo";
import {ApiImage} from "./ApiImage";
import {ApiCategory} from "./ApiCategory";

export type ApiGame = {
    id: number;
    category: ApiCategory|number;
    cover?: ApiImage | null;
    first_release_date: number | null;
    game_modes?: ApiMode[];
    genres?: ApiGenre[];
    involved_companies?: ApiInvolvedCompany[];
    name: string;
    platforms: ApiPlatform[];
    player_perspectives?: ApiPlayerPerspective[] | number[];
    screenshots?: ApiImage[];
    summary?: string | null;
    themes?: ApiTheme[];
    url: string;
    videos?: ApiVideo[];
    websites: ApiWebsite[];
};


import {ApiWebsiteCategory} from "./ApiWebsiteCategory";

export type ApiWebsite = {
    id: number;
    url: string;
    category: ApiWebsiteCategory|number;
    game: number;
    trusted: boolean;
    checksum: string;
};

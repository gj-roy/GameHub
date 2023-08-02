import {ApiReleaseDateCategory} from "./ApiReleaseDateCategory";

export type ApiReleaseDate = {
    date: number | null;
    y: number | null;
    category: ApiReleaseDateCategory;
};

import {ApiImage} from "./ApiImage";

export type ApiCompany = {
    id: number;
    name: string;
    url: string;
    logo: ApiImage | null;
    developed: number[];
};

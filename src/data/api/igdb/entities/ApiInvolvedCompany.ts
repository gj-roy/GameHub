import {ApiCompany} from "./ApiCompany";

export type ApiInvolvedCompany = {
    id: number;
    company: ApiCompany | number;
    created_at: number;
    developer: boolean;
    game: number
    publisher: boolean;
    porting: boolean;
    supporting: boolean;
    updated_at: number;
    checksum: string;
};

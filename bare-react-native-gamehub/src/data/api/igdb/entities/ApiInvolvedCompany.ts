import {ApiCompany} from "./ApiCompany";

export type ApiInvolvedCompany = {
    company: ApiCompany;
    developer: boolean;
    publisher: boolean;
    porting: boolean;
    supporting: boolean;

};

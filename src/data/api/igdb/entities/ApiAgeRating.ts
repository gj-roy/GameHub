import {ApiAgeRatingCategory} from "./ApiAgeRatingCategory";
import {ApiAgeRatingType} from "./ApiAgeRatingType";

export type ApiAgeRating = {

    category: ApiAgeRatingCategory;
    rating: ApiAgeRatingType;
};

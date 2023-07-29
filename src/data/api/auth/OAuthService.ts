import axios from "axios/index";

type ApiCallOptions = {
    method: string;
    url: string;
    params: OAuthRequestParams;
};

export type OAuthRequestParams = {
    client_id: string;
    client_secret: string;
    grant_type: string;
};


export const OAuthService = async (params: OAuthRequestParams) => {
    const options: ApiCallOptions =
        {
            method: 'POST',
            url: `https://id.twitch.tv/oauth2/token?`,
            params: params
        };

    try {
        const response = await axios.request(options);
        return response.data;
    } catch (error) {
        console.error('error: ', error);
        return null;
    }
}

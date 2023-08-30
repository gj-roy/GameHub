import {OAuthRequestParams, OAuthService} from "./OAuthService";
import {TWITCH_APP_CLIENT_ID, TWITCH_APP_CLIENT_SECRET} from "../../../secrets/Secrets";

export class RemoteOAuthDataSource {

    constructor() {
    }

    getCredentials() {

        const requestParams: OAuthRequestParams = {
            client_id: TWITCH_APP_CLIENT_ID,
            client_secret: TWITCH_APP_CLIENT_SECRET,
            grant_type: 'client_credentials'
        }

        return OAuthService(requestParams);

    }
}

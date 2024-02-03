import AsyncStorage from '@react-native-async-storage/async-storage';
import {LIKED_GAMES_DB_KEY} from "./Constants";

/**
 * Sets the "true" value for the given gameId. Call it when user likes a game.
 *
 * @return {boolean} - Whether the key-value pair was stored successfully.
 */
export async function AddGameToLikedListUseCase(gameId: string): Promise<boolean> {
    try {
        // First read the list of liked games(in the beginning it will be null).
        const likedGamesString: string | null = await AsyncStorage.getItem(LIKED_GAMES_DB_KEY);
        // This string should be converted to an array (if the string was null, we'll just make an empty array).
        const likedGamesArray: string[] = (likedGamesString?.split(',')) ?? [];
        // Now add the gameId to this array.
        likedGamesArray.push(gameId);
        // And finally, save the updated array as a string into the database.
        await AsyncStorage.setItem(LIKED_GAMES_DB_KEY, likedGamesArray.join(','));

        return true;
    } catch (e) {
        console.error(e);
        return false;
    }
}

/**
 * Get the liked status of a game.
 * @async
 * @returns {Promise<string | null>} - A promise that resolves when the liked status of the game is retrieved successfully. Otherwise, it throws an error.
 * @throws {Error} - If there are no previously saved like states for this gameId.
 */
export async function GetLikedGamesUseCase(): Promise<string[]> {
    try {
        // Get the string representing the list of liked games.
        const likedGamesString: string | null = await AsyncStorage.getItem(LIKED_GAMES_DB_KEY);
        // Convert the string into list of liked games(if the string was null, we'll just make an empty array).
        return (likedGamesString?.split(',')) ?? [];
    } catch (e) {
        console.error(e);
        throw e;
    }
}

/**
 * Removes a liked game from database.
 *
 * @param {string} gameId - The ID of the game to be removed.
 * @throws {Error} - If there was an error while removing the game.
 * @return {boolean} - Whether the gameId was removed successfully.
 */
export async function RemoveGameFromLikedListUseCase(gameId: string): Promise<boolean> {
    try {
        // Firstly, get liked games from database (as a string)
        const likedGamesString: string | null = await AsyncStorage.getItem(LIKED_GAMES_DB_KEY);
        // This string should be converted to an array (if the string was null, we'll just make an empty array).
        const likedGamesArray: string[] = (likedGamesString?.split(',')) ?? [];
        // Now, make a new array that doesn't contain gameId.
        const newLikedGamesArray = likedGamesArray.filter(likedGame => likedGame !== gameId);
        // And finally, the edited array should be saved into database (as a string)
        await AsyncStorage.setItem(LIKED_GAMES_DB_KEY, newLikedGamesArray.join(','));
        return true;

    } catch (e) {
        console.log(e);
        return false;
    }
}

import {createNativeStackNavigator} from "@react-navigation/native-stack";
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';

/**
 * This is the main navigator of the app; if you wanted
 * to go to any of the screens that are not in the bottom
 * tab bar, you will be using this navigator.
 */
export const myStackNavigator = {
    Stack: createNativeStackNavigator(),
    id: "stack-navigator",
};

/**
 * This is the secondary navigator of the app; it's solely
 * used for controlling the bottom tab bar of the app and is a
 * child of the "myStackNavigator".
 */
export const myTabNavigator = {
    Tab: createBottomTabNavigator(),
    name: "home"
};

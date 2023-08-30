import {createBottomTabNavigator} from "@react-navigation/bottom-tabs";
import {createNativeStackNavigator} from "@react-navigation/native-stack";
import {Text, View} from "react-native";
import {NavigationContainer, Route} from "@react-navigation/native";
import {
    Cog6ToothIcon as SettingsOutline,
    HeartIcon as HeartOutline,
    HomeIcon as HomeOutline,
    NewspaperIcon as NewspaperOutline
} from 'react-native-heroicons/outline';
import {
    Cog6ToothIcon as SettingsSolid,
    HeartIcon as HeartSolid,
    HomeIcon as HomeSolid,
    NewspaperIcon as NewspaperSolid
} from 'react-native-heroicons/solid';
import React, {ReactNode} from "react";
import {DiscoverScreen} from "../DiscoverScreen";
import {LikesScreen} from "../LikesScreen";
import {NewsScreen} from "../NewsScreen";
import {SettingsScreen} from "../SettingsScreen";
import {GameScreen} from "../GameScreen";
import {Destination} from "./Destination";
import {GameHubColors} from "../theme/GameHubTheme";


// The 2 navigators used for the navigation module of this app.
const Stack = createNativeStackNavigator();
const Tab = createBottomTabNavigator();

/**
 *
 * @param route - the Route<> that this icon is referring to.
 * @param focused - Whether that icon is in focus mode or not.
 */
const bottomBarIcons = (route: Route<any>, focused: boolean) => {
    let icon: ReactNode;


    if (route.name === Destination.Discover.route) {
        icon = focused ? <HomeSolid size={30} color={GameHubColors.secondary}/> :
            <HomeOutline size={30} color={GameHubColors.primary}/>
    } else if (route.name === Destination.Likes.route) {
        icon = focused ? <HeartSolid size={30} color={GameHubColors.secondary}/> :
            <HeartOutline size={30} color={GameHubColors.primary}/>
    } else if (route.name === Destination.News.route) {
        icon = focused ? <NewspaperSolid size={30} color={GameHubColors.secondary}/> :
            <NewspaperOutline size={30} color={GameHubColors.primary}/>
    } else if (route.name === Destination.Settings.route) {
        icon = focused ? <SettingsSolid size={30} color={GameHubColors.secondary}/> :
            <SettingsOutline size={30} color={GameHubColors.primary}/>
    }

    return (
        <View
            style={{
                alignItems: 'center',
            }}
        >
            {icon}
        </View>
    )
}

/**
 * @param route - the Route<> that this label is referring to.
 * @param focused - Whether that label is in focus mode or not.
 */
function bottomBarLabels(route: Route<any>, focused: boolean) {
    let label: ReactNode;

    if (route.name === Destination.Discover.route) {
        label = focused ?
            <Text style={{color: GameHubColors.secondary}}>{Destination.Discover.route}</Text> :
            <Text style={{color: GameHubColors.primary}}>{Destination.Discover.route}</Text>
    } else if (route.name === Destination.Likes.route) {
        label = focused ?
            <Text style={{color: GameHubColors.secondary}}>{Destination.Likes.route}</Text> :
            <Text style={{color: GameHubColors.primary}}>{Destination.Likes.route}</Text>
    } else if (route.name === Destination.News.route) {
        label = focused ?
            <Text style={{color: GameHubColors.secondary}}>{Destination.News.route}</Text> :
            <Text style={{color: GameHubColors.primary}}>{Destination.News.route}</Text>
    } else if (route.name === Destination.Settings.route) {
        label = focused ?
            <Text style={{color: GameHubColors.secondary}}>{Destination.Settings.route}</Text> :
            <Text style={{color: GameHubColors.primary}}>{Destination.Settings.route}</Text>
    }
    return label;
}

function BottomTabs() {
    return (
        <Tab.Navigator
            initialRouteName={Destination.Discover.route}
            screenOptions={({route}) => ({
                tabBarShowLabel: true,
                headerShown: true,
                headerStyle: {
                    backgroundColor: GameHubColors.neutral,
                },
                tabBarIcon: ({focused}) => bottomBarIcons(route, focused),
                tabBarLabel: ({focused}) => bottomBarLabels(route, focused),
                tabBarStyle: {
                    height: 60,
                    paddingBottom: 8,
                    paddingTop: 5,
                },
            })}

        >
            <Tab.Screen name={Destination.Discover.route} component={DiscoverScreen}/>
            <Tab.Screen name={Destination.Likes.route} component={LikesScreen}/>
            <Tab.Screen name={Destination.News.route} component={NewsScreen}/>
            <Tab.Screen name={Destination.Settings.route} component={SettingsScreen}/>
        </Tab.Navigator>
    )
}

export const GameHubNavigation = () => {
    return (
        <NavigationContainer>
            <Stack.Navigator
            >
                <Stack.Screen
                    name="home"
                    options={{headerShown: false}}
                    component={BottomTabs}
                />
                <Stack.Screen
                    name={Destination.Game.route}
                    options={{headerShown: false}}
                    component={GameScreen}
                />
            </Stack.Navigator>
        </NavigationContainer>
    )

}

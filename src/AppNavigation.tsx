import {Text, View} from 'react-native'
import React from 'react'
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import {NavigationContainer, Route} from "@react-navigation/native";
import {DiscoverScreen} from "./DiscoverScreen";
import {LikesScreen} from "./LikesScreen";
import {NewsScreen} from "./NewsScreen";
import {SettingsScreen} from "./SettingsScreen";
import {createNativeStackNavigator} from "react-native-screens/native-stack";
import {
    Cog6ToothIcon as SettingsSolid,
    HeartIcon as HeartSolid,
    HomeIcon as HomeSolid,
    NewspaperIcon as NewspaperSolid,
} from "react-native-heroicons/solid";
import {
    Cog6ToothIcon as SettingsOutline,
    HeartIcon as HeartOutline,
    HomeIcon as HomeOutline,
    NewspaperIcon as NewspaperOutline,
} from "react-native-heroicons/outline";
import {GameHubColors} from "./theme/GameHubTheme";
import {Destination} from "./Destination";
import {GameScreen} from "./GameScreen";


export const AppNavigation = () => {

    const Stack = createNativeStackNavigator();
    const Tab = createBottomTabNavigator();

    /**
     * Creates the icon of each one of the bottom bar tabs.
     */
    const bottomBarIcons = (route: Route<any>, focused: boolean) => {
        let icon;
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
     * Creates the label of each one of bottom bar tabs (it will be
     * shown below the icon in the bottom tab bar).
     */
    function bottomBarLabels(route: Route<any>, focused: boolean) {

        let label;
        if (route.name === Destination.Discover.route) {
            label = focused ?
                <Text style={{color: GameHubColors.secondary}}>{Destination.Discover.title}</Text> :
                <Text style={{color: GameHubColors.primary}}>{Destination.Discover.title}</Text>
        } else if (route.name === Destination.Likes.route) {
            label = focused ?
                <Text style={{color: GameHubColors.secondary}}>{Destination.Likes.title}</Text> :
                <Text style={{color: GameHubColors.primary}}>{Destination.Likes.title}</Text>
        } else if (route.name === Destination.News.route) {
            label = focused ?
                <Text style={{color: GameHubColors.secondary}}>{Destination.News.title}</Text> :
                <Text style={{color: GameHubColors.primary}}>{Destination.News.title}</Text>
        } else if (route.name === Destination.Settings.route) {
            label = focused ?
                <Text style={{color: GameHubColors.secondary}}>{Destination.Settings.title}</Text> :
                <Text style={{color: GameHubColors.primary}}>{Destination.Settings.title}</Text>
        }
        return label;
    }

    const BottomTabs = () => {


        return (<Tab.Navigator
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
        </Tab.Navigator>)
    }

    return (
        <NavigationContainer>
            <Stack.Navigator>
                <Stack.Screen name="Home" options={{headerShown: false}} component={BottomTabs}/>
                <Stack.Screen name="Game" options={{headerShown: false}} component={GameScreen}/>
            </Stack.Navigator>
        </NavigationContainer>
    )
}


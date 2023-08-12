import {Text, View} from 'react-native'
import React from 'react'
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import {NavigationContainer, Route} from "@react-navigation/native";
import {DiscoverScreen} from "./DiscoverScreen";
import {LikesScreen} from "./LikesScreen";
import {NewsScreen} from "./NewsScreen";
import {SettingsScreen} from "./SettingsScreen";
import {createNativeStackNavigator} from "@react-navigation/native-stack";
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

    function menuIcons(route: Route<any>, focused: boolean) {
        let icon;
        if (route.name === Destination.DiscoverTab.route) {
            icon = focused ? <HomeSolid size={30} color={GameHubColors.secondary}/> :
                <HomeOutline size={30} color={GameHubColors.primary}/>
        } else if (route.name === Destination.LikesTab.route) {
            icon = focused ? <HeartSolid size={30} color={GameHubColors.secondary}/> :
                <HeartOutline size={30} color={GameHubColors.primary}/>
        } else if (route.name === Destination.NewsTab.route) {
            icon = focused ? <NewspaperSolid size={30} color={GameHubColors.secondary}/> :
                <NewspaperOutline size={30} color={GameHubColors.primary}/>
        } else if (route.name === Destination.SettingsTab.route) {
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

    function menuLabels(route: Route<any>, focused: boolean) {

        let label;
        if (route.name === Destination.DiscoverTab.route) {
            label = focused ?
                <Text style={{color: GameHubColors.secondary}}>{Destination.DiscoverTab.title}</Text> :
                <Text style={{color: GameHubColors.primary}}>{Destination.DiscoverTab.title}</Text>
        } else if (route.name === Destination.LikesTab.route) {
            label = focused ?
                <Text style={{color: GameHubColors.secondary}}>{Destination.LikesTab.title}</Text> :
                <Text style={{color: GameHubColors.primary}}>{Destination.LikesTab.title}</Text>
        } else if (route.name === Destination.NewsTab.route) {
            label = focused ?
                <Text style={{color: GameHubColors.secondary}}>{Destination.NewsTab.title}</Text> :
                <Text style={{color: GameHubColors.primary}}>{Destination.NewsTab.title}</Text>
        } else if (route.name === Destination.SettingsTab.route) {
            label = focused ?
                <Text style={{color: GameHubColors.secondary}}>{Destination.SettingsTab.title}</Text> :
                <Text style={{color: GameHubColors.primary}}>{Destination.SettingsTab.title}</Text>
        }
        return label;
    }

    function DiscoverTabStack() {
        return (
            <Stack.Navigator>
                <Stack.Screen name={Destination.DiscoverScreen.route} component={DiscoverScreen}/>
                <Stack.Screen name={Destination.GameScreen.route} component={GameScreen}/>
            </Stack.Navigator>
        );
    }

    return (
        <NavigationContainer>

            <Tab.Navigator
                id="bottom-tab-navigator"
                initialRouteName={Destination.DiscoverTab.route}
                screenOptions={({route}) => ({
                    tabBarShowLabel: true,
                    headerShown: true,
                    headerStyle: {
                        backgroundColor: GameHubColors.neutral,
                    },
                    tabBarIcon: ({focused}) => menuIcons(route, focused),
                    tabBarLabel: ({focused}) => menuLabels(route, focused),
                    tabBarStyle: {
                        height: 60,
                        paddingBottom: 8,
                        paddingTop: 5,
                    },
                })}
            >
                <Tab.Screen name={Destination.DiscoverTab.route} component={DiscoverTabStack}/>
                <Tab.Screen name={Destination.LikesTab.route} component={LikesScreen}/>
                <Tab.Screen name={Destination.NewsTab.route} component={NewsScreen}/>
                <Tab.Screen name={Destination.SettingsTab.route} component={SettingsScreen}/>
            </Tab.Navigator>
        </NavigationContainer>
    )
}


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
    HomeIcon as HomeSolid,
    HeartIcon as HeartSolid,
    NewspaperIcon as NewspaperSolid,
    Cog6ToothIcon as SettingsSolid,
} from "react-native-heroicons/solid";
import {
    HomeIcon as HomeOutline,
    HeartIcon as HeartOutline,
    NewspaperIcon as NewspaperOutline,
    Cog6ToothIcon as SettingsOutline,
} from "react-native-heroicons/outline";
import {GameHubColors} from "./theme/GameHubTheme";
import {Destination} from "./Destination";


export const AppNavigation = () => {

    const Stack = createNativeStackNavigator();
    const Tab = createBottomTabNavigator();

    const menuIcons = (route: Route<any>, focused: boolean) => {
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

    function menuLabels(route: Route<any>, focused: boolean) {

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

                tabBarIcon: ({focused}) => menuIcons(route, focused),
                tabBarLabel: ({focused}) => menuLabels(route, focused),
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
            </Stack.Navigator>
        </NavigationContainer>
    )
}


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

export const AppNavigation = () => {

    const Stack = createNativeStackNavigator();
    const Tab = createBottomTabNavigator();

    const menuIcons = (route: Route<any>, focused: boolean) => {
        let icon;
        if (route.name === 'discover') {
            icon = focused ? <HomeSolid size={30} color="#f64b59"/> : <HomeOutline size={30} color="#4f5c68"/>
        } else if (route.name === 'likes') {
            icon = focused ? <HeartSolid size={30} color="#f64b59"/> : <HeartOutline size={30} color="#4f5c68"/>
        } else if (route.name === 'news') {
            icon = focused ? <NewspaperSolid size={30} color="#f64b59"/> : <NewspaperOutline size={30} color="#4f5c68"/>
        } else if (route.name === 'settings') {
            icon = focused ? <SettingsSolid size={30} color="#f64b59"/> : <SettingsOutline size={30} color="#4f5c68"/>
        }

        return (
            <View
                style={{
                    alignItems: 'center',
                    backgroundColor: focused ? 'white' : undefined
                }}
            >
                {icon}
            </View>
        )
    }

    function menuLabels(route: Route<any>, focused: boolean) {

        let label;
        if (route.name === 'discover') {
            label = focused ? <Text style={{color: '#f64b59'}}>Discover</Text> :
                <Text style={{color: '#4f5c68'}}>Discover</Text>
        } else if (route.name === 'likes') {
            label = focused ? <Text style={{color: '#f64b59'}}>Likes</Text> :
                <Text style={{color: '#4f5c68'}}>likes</Text>
        } else if (route.name === 'news') {
            label = focused ? <Text style={{color: '#f64b59'}}>News</Text> : <Text style={{color: '#4f5c68'}}>News</Text>
        } else if (route.name === 'settings') {
            label = focused ? <Text style={{color: '#f64b59'}}>Settings</Text> :
                <Text style={{color: '#4f5c68'}}>Settings</Text>
        }
        return label;
    }

    const BottomTabs = () => {


        return (<Tab.Navigator
            initialRouteName="discover"
            screenOptions={({route}) => ({
                tabBarShowLabel: true,
                headerShown: true,
                tabBarIcon: ({focused}) => menuIcons(route, focused),
                tabBarLabel: ({focused}) => menuLabels(route, focused),
                tabBarStyle: {
                    height: 60,
                    paddingBottom: 8,
                },
            })}

        >
            <Tab.Screen name="discover" component={DiscoverScreen}/>
            <Tab.Screen name="likes" component={LikesScreen}/>
            <Tab.Screen name="news" component={NewsScreen}/>
            <Tab.Screen name="settings" component={SettingsScreen}/>
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


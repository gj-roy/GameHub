import {View, Text, Image} from 'react-native'
import React from 'react'
import {createMaterialBottomTabNavigator} from '@react-navigation/material-bottom-tabs';
import {NavigationContainer, Route} from "@react-navigation/native";
import {DiscoverScreen} from "./DiscoverScreen";
import {LikesScreen} from "./LikesScreen";
import {NewsScreen} from "./NewsScreen";
import {SettingsScreen} from "./SettingsScreen";
import {createNativeStackNavigator} from "react-native-screens/native-stack";


export const AppNavigation = () => {

    const Stack = createNativeStackNavigator();
    const Tab = createMaterialBottomTabNavigator();

    const menuIcons = (route: Route<any>, focused: boolean) => {
        let icon;
        if (route.name === 'discover') {
            icon = focused ? <Image style={{
                    tintColor: 'red',
                    backgroundColor: 'white',
                }} source={require('./assets/icons/compass_rose.xml')}/> :
                <Image source={require('./assets/icons/compass_rose.xml')}/>
        } else if (route.name === 'likes') {
            icon = focused ? <Image style={{
                    tintColor: 'red',
                    backgroundColor: 'white',
                }} source={require('./assets/icons/heart.xml')}/> :
                <Image source={require('./assets/icons/heart.xml')}/>
        } else if (route.name === 'news') {
            icon = focused ? <Image style={{
                    tintColor: 'red',
                    backgroundColor: 'white',
                }} source={require('./assets/icons/newspaper.xml')}/> :
                <Image source={require('./assets/icons/newspaper.xml')}/>
        } else if (route.name === 'settings') {
            icon = focused ? <Image style={{
                    tintColor: 'red',
                    backgroundColor: 'white',
                }} source={require('./assets/icons/cog_outline.xml')}/> :
                <Image source={require('./assets/icons/cog_outline.xml')}/>
        }

        return (
            <View
                style={{
                    alignItems: 'center',
                    borderRadius: 100,
                    padding: 8,
                }}
            >
                {icon}
            </View>
        )
    }

    const BottomTabs = () => <Tab.Navigator
        screenOptions={({route}) => ({
            tabBarIcon: ({focused}) => menuIcons(route, focused),
            tabBarStyle: {
                height: 60,
                marginVertical: 5,
                borderRadius: 50,
                marginHorizontal: 10,
                position: 'absolute'
            }
        })}
    >
        <Tab.Screen name="discover" component={DiscoverScreen}/>
        <Tab.Screen name="likes" component={LikesScreen}/>
        <Tab.Screen name="news" component={NewsScreen}/>
        <Tab.Screen name="settings" component={SettingsScreen}/>
    </Tab.Navigator>

    return (
        <NavigationContainer>
            <Stack.Navigator screenOptions={{
                contentStyle: {backgroundColor: 'white'}
            }}>
                <Stack.Screen name="Home" options={{}} component={BottomTabs}/>
            </Stack.Navigator>
        </NavigationContainer>
    )
}


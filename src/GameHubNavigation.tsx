import {createBottomTabNavigator} from "@react-navigation/bottom-tabs";
import {createNativeStackNavigator} from "@react-navigation/native-stack";
import {View} from "react-native";
import {NavigationContainer, Route} from "@react-navigation/native";
import {
    HeartIcon as HeartOutline,
    HomeIcon as HomeOutline,
    ShoppingBagIcon as BagOutline
} from 'react-native-heroicons/outline';
import {
    HeartIcon as HeartSolid,
    HomeIcon as HomeSolid,
    ShoppingBagIcon as BagSolid
} from 'react-native-heroicons/solid';
import {HomeScreen} from "./HomeScreen";
import {ProductScreen} from "./ProductScreen";
import {themeColors} from "./CoffeeTheme";
import {ReactNode} from "react";


// The 2 navigators used for the navigation module of this app.
const Stack = createNativeStackNavigator();
const Tab = createBottomTabNavigator();

const BottomIcons = (route: Route<any>, focused: boolean) => {
    let icon: ReactNode;


    if (route.name === 'home') {
        icon = focused ? <HomeSolid size="30" color={themeColors.bgLight}/> :
            <HomeOutline size="30" strokeWidth={2} color="white"/>
    } else if (route.name === 'favourite') {
        icon = focused ? <HeartSolid size="30" color={themeColors.bgLight}/> :
            <HeartOutline size="30" strokeWidth={2} color="white"/>
    } else if (route.name === 'cart') {
        icon = focused ? <BagSolid size="30" color={themeColors.bgLight}/> :
            <BagOutline size="30" strokeWidth={2} color="white"/>
    }

    return (
        <View
            style={{
                alignItems: 'center',
                borderRadius: 30,
                padding: 10,
                backgroundColor: focused ? "white" : undefined,
            }}
        >
            {icon}
        </View>
    )
}

function BottomTabs() {
    return (
        <Tab.Navigator screenOptions={({route}) => ({
            headerShown: false,
            tabBarShowLabel: true,
            tabBarLabelStyle: {
                fontSize: 16,
                color: 'black',
                marginVertical: 5
            },
            tabBarIcon: ({focused}) => BottomIcons(route, focused),
            tabBarStyle: {
                height: 85,
                alignItems: 'center',
                backgroundColor: themeColors.bgLight,
            },
            tabBarItemStyle: {
                marginTop: 5
            }
        })}

        >
            <Tab.Screen name="home" component={HomeScreen}/>
            <Tab.Screen name="favourite" component={HomeScreen}/>
            <Tab.Screen name="cart" component={HomeScreen}/>
        </Tab.Navigator>
    )
}

export const GameHubNavigation = () => {
    return (
        <NavigationContainer>
            <Stack.Navigator screenOptions={{
                contentStyle: {backgroundColor: 'white'}
            }}>
                <Stack.Screen
                    name="Home"
                    options={{headerShown: false}}
                    component={BottomTabs}
                />
                <Stack.Screen
                    name="Product"
                    options={{headerShown: false}}
                    component={ProductScreen}
                />
            </Stack.Navigator>
        </NavigationContainer>
    )

}


import React, {useState} from 'react';
import {Dimensions, FlatList, Image, Text, TextInput, TouchableOpacity, View} from "react-native";
import {StatusBar} from 'expo-status-bar';
import {SafeAreaView} from 'react-native-safe-area-context'
import {themeColors} from "./CoffeeTheme";
import {BellIcon, MagnifyingGlassIcon} from 'react-native-heroicons/outline'
import {MapPinIcon} from 'react-native-heroicons/solid'
import Carousel from 'react-native-snap-carousel';
import {categories, coffeeItems} from "./FakeData";
import {CoffeeCard} from "./coffeeCard";


const {width, height} = Dimensions.get('window');

export function HomeScreen() {
    const [activeCategory, setActiveCategory] = useState(1);

    return (
        <View
            style={{
                flex: 1,
                backgroundColor: 'white'
            }}
        >
            <StatusBar/>

            <Image
                source={require('../assets/beansBackground1.png')}
                style={{
                    height: height * 0.2,
                    width: '100%',
                    position: 'absolute',
                    marginTop: -25,
                    opacity: 0.15,
                }}
            />
            <SafeAreaView>
                {/* avatar and bell icon */}
                <View
                    style={{
                        marginTop: 10,
                        marginHorizontal: 15,
                        flexDirection: 'row',
                        justifyContent: 'space-between',
                        alignItems: 'center',
                    }}
                >
                    <Image source={require('../assets/avatar.png')}
                           style={{
                               height: 40,
                               width: 40,
                               borderRadius: 30,
                           }}
                    />

                    <View
                        style={{
                            flexDirection: 'row',
                            alignItems: 'center',
                        }}
                    >
                        <MapPinIcon size="25" color={themeColors.bgLight}/>
                        <Text
                            style={{
                                fontWeight: '700',
                                fontSize: 18,
                            }}
                        >
                            New York, NYC
                        </Text>
                    </View>
                    <BellIcon size="27" color="black"/>
                </View>
                {/* search bar */}
                <View
                    style={{
                        marginTop: height * 0.06,
                        marginHorizontal: 10,
                    }}
                >
                    <View
                        style={{
                            flexDirection: 'row',
                            alignItems: 'center',
                            borderRadius: 30,
                            padding: 10,
                            backgroundColor: '#e6e6e6',
                        }}
                    >
                        <TextInput
                            placeholder='Search'
                            style={{
                                padding: 5,
                                flex: 1,
                                fontWeight: '700',
                                color: 'rgb(55 65 81)'
                            }}
                        />
                        <TouchableOpacity
                            style={{
                                backgroundColor: themeColors.bgLight,
                                borderRadius: 30,
                                padding: 10
                            }}>
                            <MagnifyingGlassIcon size="25" strokeWidth={2} color="white"/>
                        </TouchableOpacity>
                    </View>
                </View>
                {/* categories */}
                <View
                    style={{
                        paddingHorizontal: 10,
                        marginTop: 15,
                    }}
                >
                    <FlatList
                        horizontal
                        showsHorizontalScrollIndicator={false}
                        data={categories}
                        keyExtractor={item => item.id.toString()}
                        style={{
                            overflow: 'visible',
                        }}
                        renderItem={({item}) => {
                            const isActive = item.id === activeCategory;


                            return (
                                <TouchableOpacity
                                    onPress={() => setActiveCategory(item.id)}
                                    style={{
                                        backgroundColor: isActive ? themeColors.bgLight : 'rgba(156,107,107,0.07)',
                                        paddingVertical: 15,
                                        paddingHorizontal: 15,
                                        marginLeft: 10,
                                        borderRadius: 25,
                                    }}
                                >
                                    <Text
                                        style={{
                                            fontWeight: '700',
                                            color: isActive ? 'white' : 'rgb(55 65 81)',
                                        }}
                                    >
                                        {item.title}
                                    </Text>
                                </TouchableOpacity>
                            )
                        }}
                    />
                </View>

            </SafeAreaView>

            {/* coffee cards */}
            <View
                style={{
                    overflow: 'visible',
                    justifyContent: 'center',
                    flex: 1,
                }}
            >
                <View>
                    <Carousel
                        containerCustomStyle={{overflow: 'visible'}}
                        data={coffeeItems}
                        renderItem={({item}) => <CoffeeCard item={item}/>}
                        firstItem={1}
                        loop={false}
                        inactiveSlideScale={0.75}
                        inactiveSlideOpacity={0.75}
                        sliderWidth={width}
                        itemWidth={width * 0.63}
                        slideStyle={{display: 'flex', alignItems: 'center'}}
                        vertical={false}/>
                </View>

            </View>


        </View>
    )
}


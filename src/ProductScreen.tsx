import {Image, Text, TouchableOpacity, View} from 'react-native'
import React, {useState} from 'react'
import {useNavigation, useRoute} from '@react-navigation/native'
import {StatusBar} from 'expo-status-bar';
import {ArrowLeftCircleIcon, MinusIcon, PlusIcon, ShoppingBagIcon} from 'react-native-heroicons/outline';
import {HeartIcon, StarIcon} from 'react-native-heroicons/solid';
import {themeColors} from './CoffeeTheme';


export const ProductScreen = () => {

    // @ts-ignore
    const {name, price, volume, stars, image, desc} = useRoute().params;
    const navigation = useNavigation();
    const [orderSize, setOrderSize] = useState('small');

    return (
        <View style={{
            flex: 1
        }}>
            <StatusBar style="light"/>
            <Image
                source={require('../assets/beansBackground2.png')}
                style={{
                    width: '100%',
                    height: 120,
                    borderBottomLeftRadius: 50,
                    borderBottomRightRadius: 50,
                    position: 'absolute'
                }}
            />
            <View style={{marginTop: 24}}>

                {/*The row containing back button and the like button.*/}
                <View
                    style={{
                        marginHorizontal: 8,
                        flexDirection: 'row',
                        justifyContent: 'space-between',
                        alignItems: 'center',
                    }}
                >
                    <TouchableOpacity onPress={() => navigation.goBack()}>
                        <ArrowLeftCircleIcon size={50} strokeWidth={1.2} color="white"/>
                    </TouchableOpacity>

                    <TouchableOpacity
                        style={{
                            borderRadius: 100,
                            borderWidth: 2,
                            borderColor: 'white',
                            padding: 6,
                        }}
                    >
                        <HeartIcon size={24} color="white"/>
                    </TouchableOpacity>
                </View>


                <Image source={image}
                       style={{
                           marginTop: -30,
                           height: 120,
                           width: 120,
                           alignSelf: 'center'
                       }}
                />

                {/*Rate of the coffee item out of 5*/}
                <View
                    style={{
                        width: 70,
                        height: 40,
                        backgroundColor: themeColors.bgLight,
                        flexDirection: 'row',
                        marginHorizontal: 14,
                        alignItems: 'center',
                        borderRadius: 19,
                        paddingVertical: 6,
                        paddingHorizontal: 12,
                        justifyContent: 'center',
                    }}
                >
                    <StarIcon size="15" color="white"/>
                    <Text
                        style={{
                            fontSize: 16,
                            fontWeight: '600',
                            color: 'white',
                            marginLeft: 8
                        }}

                    >{stars}</Text>
                </View>

                {/*The row containing name and price of the coffee item.*/}
                <View
                    style={{
                        flexDirection: 'row',
                        justifyContent: 'space-between',
                        marginHorizontal: 14,
                        alignItems: 'center',
                        marginTop: 22
                    }}
                >
                    <Text style={{
                        color: themeColors.text,
                        fontSize: 30,
                        lineHeight: 30,
                        fontWeight: '600',

                    }}>{name}</Text>
                    <Text style={{
                        color: themeColors.text,
                        fontSize: 18,
                        lineHeight: 18,
                        fontWeight: '600',

                    }}>$ {price}</Text>
                </View>

                {/*The row for choosing coffee size*/}
                <View
                    style={{
                        marginHorizontal: 14,
                        marginTop: 18
                    }}
                >
                    <Text
                        style={{
                            color: themeColors.text,
                            fontSize: 18,
                            lineHeight: 18,
                            fontWeight: 'bold'
                        }}>
                        Coffee size
                    </Text>
                    <View
                        style={{
                            flexDirection: 'row',
                            justifyContent: 'space-around',
                            marginTop: 8,
                        }}
                    >
                        <TouchableOpacity
                            onPress={() => setOrderSize('small')}
                            style={{
                                backgroundColor: orderSize === 'small' ? themeColors.bgLight : 'rgba(0, 0, 0, 0.07)',
                                paddingHorizontal: 25,
                                paddingVertical: 10,
                                borderRadius: 80,
                                alignItems: 'center',
                                justifyContent: 'center'
                            }}
                        >
                            <Text
                                style={{
                                    color: orderSize === 'small' ? 'white' : "rgb(55 65 81)",
                                }}
                            >Small</Text>
                        </TouchableOpacity>

                        <TouchableOpacity
                            onPress={() => setOrderSize('medium')}
                            style={{
                                backgroundColor: orderSize === 'medium' ? themeColors.bgLight : 'rgba(0, 0, 0, 0.07)',
                                paddingHorizontal: 25,
                                paddingVertical: 10,
                                borderRadius: 80,
                                alignItems: 'center',
                                justifyContent: 'center'
                            }}
                        >
                            <Text
                                style={{
                                    color: orderSize === 'medium' ? "white" : "rgb(55 65 81)",

                                }}
                            >Medium</Text>
                        </TouchableOpacity>

                        <TouchableOpacity
                            onPress={() => setOrderSize('large')}
                            style={{
                                backgroundColor: orderSize === 'large' ? themeColors.bgLight : 'rgba(0, 0, 0, 0.07)',
                                paddingHorizontal: 25,
                                paddingVertical: 10,
                                borderRadius: 80,
                                alignItems: 'center',
                                justifyContent: 'center'
                            }}
                        >
                            <Text
                                style={{
                                    color: orderSize === 'large' ? "white" : "rgb(55 65 81)"
                                }}
                            >Large</Text>
                        </TouchableOpacity>
                    </View>
                </View>

                {/*About section*/}
                <View
                    style={{
                        marginHorizontal: 16,
                        marginTop: 12,
                    }}
                >
                    <Text style={{
                        color: themeColors.text,
                        fontSize: 20,
                        fontWeight: 'bold',
                    }}>
                        About
                    </Text>
                    <Text
                        style={{
                            color: 'rgb(75 85 99)',
                            marginTop: 4,
                        }}>

                        {desc}
                    </Text>
                </View>

                <View
                    style={{
                        flexDirection: 'row',
                        justifyContent: 'space-between',
                        alignItems: 'center',
                        marginHorizontal: 14,
                        marginTop: 18,
                    }}
                >

                    {/*Volume of the coffee item.*/}
                    <View style={{
                        flexDirection: 'row',
                        alignItems: 'center',
                    }}>
                        <Text
                            style={{
                                fontSize: 16,
                                color: 'rgb(55 65 81)',
                                fontWeight: '600',
                                opacity: 0.6,
                            }}
                        >Volume</Text>
                        <Text
                            style={{
                                fontSize: 16,
                                color: 'black',
                                fontWeight: '600',
                                marginLeft: 6,
                            }}>

                            {volume}
                        </Text>
                    </View>

                    {/*The view for choosing the number of items to order.*/}
                    <View
                        style={{
                            flexDirection: 'row',
                            alignItems: 'center',
                            borderWidth: 1,
                            borderColor: 'rgb(107 114 128)',
                            borderRadius: 100,
                            paddingVertical: 6,
                            paddingHorizontal: 16,
                        }}
                    >
                        <TouchableOpacity>
                            <MinusIcon size={20} color={themeColors.text} strokeWidth={3}/>
                        </TouchableOpacity>
                        <Text
                            style={{
                                color: themeColors.text,
                                fontWeight: '800',
                                fontSize: 18,
                                marginLeft: 16,
                            }}
                        >2</Text>
                        <TouchableOpacity style={{marginLeft: 16}}>
                            <PlusIcon size={20} color={themeColors.text} strokeWidth={3}/>
                        </TouchableOpacity>
                    </View>

                </View>

                {/*Buy button*/}
                <View
                    style={{
                        flexDirection: 'row',
                        alignItems: 'center',
                        marginHorizontal: 14,
                        marginTop: 20
                    }}
                >
                    <TouchableOpacity
                        style={{
                            borderWidth: 1,
                            borderColor: 'lightslategray',
                            borderRadius: 100,
                            padding: 17,
                        }}
                    >
                        <ShoppingBagIcon size={30} color="lightslategray"/>
                    </TouchableOpacity>
                    <TouchableOpacity
                        style={{
                            backgroundColor: themeColors.bgLight,
                            borderRadius: 100,
                            flex: 1,
                            padding: 20,
                            marginLeft: 14,
                            alignItems: 'center'
                        }}
                    >
                        <Text
                            style={{
                                fontSize: 18,
                                fontWeight: '600',
                                color: 'white'
                            }}
                        >
                            Buy now
                        </Text>
                    </TouchableOpacity>
                </View>

            </View>
        </View>
    )
}

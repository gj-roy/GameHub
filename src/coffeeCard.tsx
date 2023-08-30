import {Dimensions, Image, Text, TouchableOpacity, View} from 'react-native'
import React from 'react'
import {themeColors} from './theme/CoffeeTheme'
import {useNavigation} from '@react-navigation/native'
import {StarIcon} from 'react-native-heroicons/solid';
import {PlusIcon} from 'react-native-heroicons/outline';
import {Coffee} from "./FakeData";

const {width, height} = Dimensions.get('window');

export type CoffeeCardProps = {
    item: Coffee;
};

export const CoffeeCard = ({item}: CoffeeCardProps) => {

    const navigation = useNavigation();

    return (
        <View
            style={{
                borderRadius: 45,
                backgroundColor: themeColors.bgDark,
                height: height * 0.48,
                width: width * 0.65,
            }}
        >
            <View
                style={{
                    marginTop: 10,
                    flexDirection: 'row',
                    justifyContent: 'center'
                }}
            >
                <Image
                    // @ts-ignore
                    source={item.image}
                    style={{
                        height: 150,
                        width: 150,
                    }}
                />
            </View>
            <View
                style={{
                    padding: 15,
                    flex: 1,
                    justifyContent: 'space-between',
                }}
            >

                <Text
                    style={{
                        fontSize: 32,
                        color: 'white',
                        fontWeight: '600',
                        zIndex: 10,
                    }}
                >
                    {item.name}
                </Text>
                <View style={{
                    backgroundColor: 'rgba(255,255,255,0.2)',
                    flexDirection: 'row',
                    alignItems: 'center',
                    borderRadius: 12,
                    paddingHorizontal: 6,
                    paddingVertical: 2,
                    width: 50,
                }}
                >
                    <StarIcon size="15" color="white"/>
                    <Text
                        style={{
                            fontSize: 14,
                            textAlign: 'center',
                            fontWeight: 'bold',
                            color: 'white',
                            paddingLeft: 3,
                        }}
                    >{item.stars}</Text>
                </View>
                <View
                    style={{
                        flexDirection: 'row',
                        zIndex: 10,
                        alignItems: 'center'
                    }}
                >
                    <Text
                        style={{
                            color: 'white',
                            fontWeight: 'bold',
                            fontSize: 15,
                            opacity: 0.7,
                        }}
                    >
                        Volume
                    </Text>
                    <Text
                        style={{
                            fontSize: 16,
                            color: 'white',
                            fontWeight: '600',
                        }}
                    > {item.volume}</Text>
                </View>


                <View style={{
                    backgroundColor: 'transparent',
                    flexDirection: 'row',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                }}
                >
                    <Text
                        style={{
                            color: 'white',
                            fontWeight: 'bold',
                            fontSize: 18
                        }}
                    >$ {item.price}</Text>
                    <TouchableOpacity
                        //@ts-ignore
                        onPress={() => navigation.navigate('Product', {...item})}
                        style={{
                            padding: 10,
                            backgroundColor: 'white',
                            borderRadius: 35,
                        }}
                    >
                        <PlusIcon size="25" strokeWidth={2} color={themeColors.bgDark}/>
                    </TouchableOpacity>
                </View>


            </View>

        </View>

    )
}

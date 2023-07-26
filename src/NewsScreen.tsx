import {View, Text} from 'react-native'
import React from 'react'
import {NewsItemDataModel, NewsScreenItem} from "./ui/NewsScreenItem";

export const NewsScreen = () => {

    const fakeNewsItem: NewsItemDataModel = {
        id: 1,
        imageUrl: 'https://www.gamespot.com/a/uploads/original/123/1239113/4169628-2932131235-39760.jpg',
        title: 'Women Continue To Represent More Of The Gaming Audience, Especially On Switch',
        lede: 'More women play video games than you might think.',
        publicationDate: 'Jul. 24, 3:44 p.m.'
    };

    return (
        <View style={{
            flex: 1,
        }}>
            <NewsScreenItem
                id={fakeNewsItem.id}
                imageUrl={fakeNewsItem.imageUrl}
                title={fakeNewsItem.title}
                lede={fakeNewsItem.lede}
                publicationDate={fakeNewsItem.publicationDate}
            />
        </View>
    )
}

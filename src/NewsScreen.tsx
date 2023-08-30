import {FlatList, View} from 'react-native'
import React, {useEffect, useState} from 'react'
import {NewsScreenItem} from "./ui/NewsScreenItem";
import {RemoteNewsArticlesDataSource} from "./data/api/news/RemoteNewsArticlesDataSource";
import {DomainNewsArticle} from "./data/domain/news/DomainNewsArticle";
import {convertApiNewsArticleToDomainNewsArticle} from "./data/api/news/entities/ApiNewsArticle";


export const NewsScreen = () => {

    const [newsArticles, setNewsArticles] = useState<DomainNewsArticle[]>([]);

    const getRemoteNewsArticles = async () => {
        const dataSource = new RemoteNewsArticlesDataSource();
        return await dataSource.getArticles()
    }

    useEffect(() => {
        getRemoteNewsArticles().then(apiResult => {


            setNewsArticles(apiResult?.results.map((apiNewsArticle) => {
                return convertApiNewsArticleToDomainNewsArticle(apiNewsArticle)
            }) ?? []);

        });
    }, []);

    return (
        <View style={{
            flex: 1,
        }}>

            <FlatList
                data={newsArticles}
                renderItem={({item}) =>
                    <NewsScreenItem
                        id={item.id}
                        imageUrl={item.image}
                        title={item.title}
                        lede={item.lede}
                        publicationDate={item.publish_date}
                    />
                }
                keyExtractor={item => item.id.toString()}
            />


        </View>
    )
}

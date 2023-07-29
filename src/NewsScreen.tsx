import {FlatList, View} from 'react-native'
import React, {useEffect, useState} from 'react'
import {NewsScreenItem} from "./ui/NewsScreenItem";
import {ApiNewsResult} from "./data/api/ApiNewsEntities";
import {RemoteNewsArticlesDataSource} from "./data/api/RemoteNewsArticlesDataSource";
import {DomainNewsArticle} from "./data/entities/NewsArticle";


export const NewsScreen = () => {

    const [newsArticles, setNewsArticles] = useState<DomainNewsArticle[]>([]);

    const getRemoteNewsArticles = async () => {
        const dataSource = new RemoteNewsArticlesDataSource();
        const data: ApiNewsResult | null = await dataSource.getArticles();
        return data
    }


    useEffect(() => {
        getRemoteNewsArticles().then(apiResult => {


            setNewsArticles(apiResult?.results.map((apiNewsArticle) => {
                return {
                    id: apiNewsArticle?.id ?? -1,
                    title: apiNewsArticle?.title ?? " ",
                    lede: apiNewsArticle?.lede ?? "",
                    publish_date: apiNewsArticle?.publish_date ?? "",
                    site_detail_url: apiNewsArticle?.site_detail_url ?? "",
                    image: apiNewsArticle?.image.original ?? ""
                }
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

import {View} from 'react-native'
import React, {useEffect, useState} from 'react'
import {NewsScreenItem} from "./ui/NewsScreenItem";
import {ApiNewsResult} from "./data/api/ApiNewsEntities";
import {RemoteNewsArticlesDataSource} from "./data/api/RemoteNewsArticlesDataSource";
import {DomainNewsArticle} from "./data/entities/NewsArticle";


export const NewsScreen = () => {

    const [sampleNewsItem, setSampleNewsItem] = useState<DomainNewsArticle>({
        id: -1,
        title: "",
        lede: "",
        publish_date: "",
        site_detail_url: "",
        image: "",
    });
    const getRemoteNewsArticles = async () => {
        const dataSource = new RemoteNewsArticlesDataSource();
        const data: ApiNewsResult | null = await dataSource.getArticles();
        return data
    }


    useEffect(() => {
        getRemoteNewsArticles().then(apiResult => {

            const sampleNewsArticle = apiResult?.results[10] ?? null;

            setSampleNewsItem({
                id: sampleNewsArticle?.id ?? -1,
                title: sampleNewsArticle?.title ?? " ",
                lede: sampleNewsArticle?.lede ?? "",
                publish_date: sampleNewsArticle?.publish_date ?? "",
                site_detail_url: sampleNewsArticle?.site_detail_url ?? "",
                image: sampleNewsArticle?.image.original ?? ""
            })

        });
    }, []);

    return (
        <View style={{
            flex: 1,
        }}>
            <NewsScreenItem
                id={sampleNewsItem.id}
                imageUrl={sampleNewsItem.image}
                title={sampleNewsItem.title}
                lede={sampleNewsItem.lede}
                publicationDate={sampleNewsItem.publish_date}
            />
        </View>
    )
}

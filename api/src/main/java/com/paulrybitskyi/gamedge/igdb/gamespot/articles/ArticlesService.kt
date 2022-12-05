package com.paulrybitskyi.gamedge.igdb.gamespot.articles

import com.paulrybitskyi.gamedge.igdb.common.ApiResult
import com.paulrybitskyi.gamedge.igdb.gamespot.articles.entities.ApiArticle
import com.paulrybitskyi.gamedge.igdb.gamespot.common.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

internal interface ArticlesService {

    @GET("articles")
    suspend fun getArticles(
        @QueryMap queryParams: Map<String, String>
    ): ApiResult<Response<ApiArticle>>
}

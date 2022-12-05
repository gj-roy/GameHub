/*
 * Copyright 2021 Paul Rybitskyi, paul.rybitskyi.work@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.paulrybitskyi.gamedge.igdb.gamespot.articles

import com.paulrybitskyi.gamedge.igdb.common.asConverterFactory
import com.paulrybitskyi.gamedge.igdb.common.calladapter.ApiResultCallAdapterFactory
import com.paulrybitskyi.gamedge.igdb.gamespot.common.GamespotConstantsProvider
import com.paulrybitskyi.gamedge.igdb.gamespot.common.di.Endpoint
import com.paulrybitskyi.gamedge.igdb.gamespot.common.di.GamespotApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ArticlesEndpointModule {

    @Provides
    fun provideArticlesService(@Endpoint(Endpoint.Type.ARTICLES) retrofit: Retrofit): ArticlesService {
        return retrofit.create(ArticlesService::class.java)
    }

    @Provides
    @Endpoint(Endpoint.Type.ARTICLES)
    fun provideRetrofit(
        @GamespotApi okHttpClient: OkHttpClient,
        @GamespotApi callAdapterFactory: ApiResultCallAdapterFactory,
        json: Json,
        gamespotConstantsProvider: GamespotConstantsProvider
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(json.asConverterFactory())
            .baseUrl(gamespotConstantsProvider.apiBaseUrl)
            .build()
    }
}

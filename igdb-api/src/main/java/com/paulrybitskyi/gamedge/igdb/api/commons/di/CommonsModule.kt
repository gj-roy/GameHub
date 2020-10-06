/*
 * Copyright 2020 Paul Rybitskyi, paul.rybitskyi.work@gmail.com
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

package com.paulrybitskyi.gamedge.igdb.api.commons.di

import com.paulrybitskyi.gamedge.igdb.api.BuildConfig
import com.paulrybitskyi.gamedge.igdb.api.commons.ErrorMapper
import com.paulrybitskyi.gamedge.igdb.api.commons.calladapter.ApiResultCallAdapterFactory
import com.paulrybitskyi.gamedge.igdb.api.commons.errorextractors.ErrorMessageExtractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Provider
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
internal object CommonsModule {


    @Provides
    fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient,
        apiResultCallAdapterFactory: ApiResultCallAdapterFactory
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(apiResultCallAdapterFactory)
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: Provider<HttpLoggingInterceptor>
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                if(BuildConfig.DEBUG) {
                    addInterceptor(httpLoggingInterceptor.get())
                }
            }
            .build()
    }


    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
    }


    @Provides
    fun provideApiResultCallAdapterFactory(
        errorMessageExtractor: ErrorMessageExtractor
    ): ApiResultCallAdapterFactory {
        return ApiResultCallAdapterFactory(errorMessageExtractor)
    }


    @Provides
    fun provideErrorMapper(): ErrorMapper {
        return ErrorMapper()
    }


}
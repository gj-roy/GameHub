package com.paulrybitskyi.gamedge.igdb.gamespot.common.di

import com.paulrybitskyi.gamedge.igdb.common.ErrorMessageExtractor
import com.paulrybitskyi.gamedge.igdb.common.addInterceptorAsFirstInChain
import com.paulrybitskyi.gamedge.igdb.common.calladapter.ApiResultCallAdapterFactory
import com.paulrybitskyi.gamedge.igdb.gamespot.common.GamespotConstantsProvider
import com.paulrybitskyi.gamedge.igdb.gamespot.common.GamespotQueryParamsFactory
import com.paulrybitskyi.gamedge.igdb.gamespot.common.GamespotQueryParamsFactoryImpl
import com.paulrybitskyi.gamedge.igdb.gamespot.common.UserAgentInterceptor
import com.paulrybitskyi.gamedge.igdb.gamespot.common.serialization.GamespotFieldsSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CommonsModule {

    @Provides
    @Singleton
    @GamespotApi
    fun provideOkHttpClient(
        okHttpClient: OkHttpClient,
        userAgentInterceptor: UserAgentInterceptor
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            .addInterceptorAsFirstInChain(userAgentInterceptor)
            .build()
    }

    @Provides
    @GamespotApi
    fun provideApiResultCallAdapterFactory(
        @GamespotApi errorMessageExtractor: ErrorMessageExtractor
    ): ApiResultCallAdapterFactory {
        return ApiResultCallAdapterFactory(errorMessageExtractor)
    }

    @Provides
    fun provideGamespotQueryParamsBuilder(
        gamespotFieldsSerializer: GamespotFieldsSerializer,
        gamespotConstantsProvider: GamespotConstantsProvider
    ): GamespotQueryParamsFactory {
        return GamespotQueryParamsFactoryImpl(
            gamespotFieldsSerializer = gamespotFieldsSerializer,
            apiKey = gamespotConstantsProvider.apiKey
        )
    }
}

package ca.on.hojat.gamenews.shared.api.igdb.games

import ca.on.hojat.gamenews.shared.api.common.asConverterFactory
import ca.on.hojat.gamenews.shared.api.common.calladapter.ApiResultCallAdapterFactory
import ca.on.hojat.gamenews.shared.api.igdb.common.IgdbConstantsProvider
import ca.on.hojat.gamenews.shared.api.igdb.common.di.qualifiers.Endpoint
import ca.on.hojat.gamenews.shared.api.igdb.common.di.qualifiers.IgdbApi
import ca.on.hojat.gamenews.shared.api.igdbcalypse.querybuilder.ApicalypseQueryBuilderFactory
import ca.on.hojat.gamenews.shared.api.igdbcalypse.serialization.ApicalypseSerializer
import ca.on.hojat.gamenews.shared.api.igdbcalypse.serialization.ApicalypseSerializerFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
@InstallIn(SingletonComponent::class)
internal object GamesEndpointModule {

    @Provides
    fun provideGamesService(@Endpoint(Endpoint.Type.GAMES) retrofit: Retrofit): GamesService {
        return retrofit.create(GamesService::class.java)
    }

    @Provides
    @Endpoint(Endpoint.Type.GAMES)
    fun provideRetrofit(
        @IgdbApi okHttpClient: OkHttpClient,
        @IgdbApi callAdapterFactory: ApiResultCallAdapterFactory,
        json: Json,
        igdbConstantsProvider: IgdbConstantsProvider
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(json.asConverterFactory())
            .baseUrl(igdbConstantsProvider.apiBaseUrl)
            .build()
    }

    @Provides
    fun provideApicalypseQueryBuilderFactory(): ApicalypseQueryBuilderFactory {
        return ApicalypseQueryBuilderFactory
    }

    @Provides
    fun provideApicalypseSerializer(): ApicalypseSerializer {
        return ApicalypseSerializerFactory.create()
    }
}

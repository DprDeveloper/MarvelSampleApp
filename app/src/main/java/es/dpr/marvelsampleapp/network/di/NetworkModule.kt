package es.dpr.marvelsampleapp.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.dpr.marvelsampleapp.network.service.CharacterService
import es.dpr.marvelsampleapp.network.service.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Provides
    @ApiEndpoint
    fun provideApiEndpoint(): String = "https://gateway.marvel.com/"

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        requestInterceptor: RequestInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(requestInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        @ApiEndpoint apiEndPoint: String,
        okHttpClient: OkHttpClient
    ): CharacterService = Retrofit.Builder()
        .baseUrl(apiEndPoint)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(CharacterService::class.java)

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiEndpoint
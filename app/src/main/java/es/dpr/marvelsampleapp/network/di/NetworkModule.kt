package es.dpr.marvelsampleapp.network.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.dpr.marvelsampleapp.network.service.CharacterService
import es.dpr.marvelsampleapp.network.service.ComicService
import es.dpr.marvelsampleapp.network.service.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

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
    fun provideRetrofitClient(
        @ApiEndpoint apiEndPoint: String,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiEndPoint)
        .addConverterFactory(GsonConverterFactory.create(
            GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        ))
        .client(okHttpClient)
        .build()

    @Provides
    fun provideCharactersService(retrofit: Retrofit): CharacterService = retrofit.create()

    @Provides
    fun provideComicService(retrofit: Retrofit): ComicService = retrofit.create()

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiEndpoint
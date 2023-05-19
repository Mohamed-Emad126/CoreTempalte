package com.memad.coretempalte.di

import com.google.gson.Gson
/*
import com.memad.coretempalte.BuildConfig
*/
import com.memad.coretempalte.data.remote.StartClient
import com.memad.coretempalte.utils.Constants
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(Constants.BASE_URL)
            client(client.build())
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        }.build()
    }

   /* @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(cacheInterceptor: Interceptor): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                this.addInterceptor(logging)
            }
        }.addInterceptor(cacheInterceptor)
    }*/


    @Singleton
    @Provides
    fun provideCacheInterceptor() =
        Interceptor { chain ->
            val originalResponse: Response = chain.proceed(chain.request())
            originalResponse.newBuilder()
                .header("Cache-Control", "max-age=86400")
                .build()
        }


    @Singleton
    @Provides
    fun provideStartClient(retrofit: Retrofit): StartClient {
        return retrofit.create(StartClient::class.java)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }


}
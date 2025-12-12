package com.seekho.anime.di

import android.content.Context
import com.seekho.anime.data.remote.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.seekho.anime.common.network.NetworkChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkChecker(@ApplicationContext context: Context) = NetworkChecker(context)

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder().addInterceptor(logging).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, ok: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .client(ok)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}
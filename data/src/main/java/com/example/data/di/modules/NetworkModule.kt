package com.example.data.di.modules

import com.example.data.config.DataConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val QUERY_PARAM_API_KEY = "apiKey"

    @Provides
    fun providesOkHttpClient(dataConfig: DataConfig): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor {
                val request = it.request()
                val httpUrl = request.url

                val urlWithApiKey = httpUrl.newBuilder()
                    .addQueryParameter(QUERY_PARAM_API_KEY, dataConfig.getApiKey())
                    .build()

                val newRequest = request.newBuilder().url(urlWithApiKey).build()
                it.proceed(newRequest)
            }
            .build()
    }

    @Provides
    fun providesRetrofitClient(okHttpClient: OkHttpClient, dataConfig: DataConfig): Retrofit {
        return Retrofit.Builder()
            .baseUrl(dataConfig.getBaseUrl())
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
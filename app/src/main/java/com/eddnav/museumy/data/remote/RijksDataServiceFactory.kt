package com.eddnav.museumy.data.remote

import com.eddnav.museumy.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RijksDataServiceFactory {

    fun build(moshi: Moshi): RijksDataService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(RijksDataAuthInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.RIJKS_DATA_API_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(RijksDataService::class.java)
    }
}
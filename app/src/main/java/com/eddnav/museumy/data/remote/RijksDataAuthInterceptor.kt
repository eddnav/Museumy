package com.eddnav.museumy.data.remote

import com.eddnav.museumy.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RijksDataAuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val modifiedUrl = originalUrl.newBuilder()
            .addQueryParameter(RijksDataServiceParams.API_KEY, BuildConfig.RIJKS_DATA_API_KEY)
            .build()
        val newRequest = originalRequest
            .newBuilder()
            .url(modifiedUrl)
            .build()
        return chain.proceed(newRequest)
    }
}
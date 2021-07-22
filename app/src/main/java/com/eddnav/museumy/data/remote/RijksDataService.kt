package com.eddnav.museumy.data.remote

import com.eddnav.museumy.data.remote.RijksDataServiceParams.AUTHOR
import com.eddnav.museumy.data.remote.model.ArtObject
import retrofit2.http.GET
import retrofit2.http.Query


interface RijksDataService {

    // TODO: Handle {culture} later? maybe by modifying the url with an interceptor

    @GET("en/collection")
    suspend fun collection(): List<ArtObject>

    @GET("en/collection")
    suspend fun collection(@Query(AUTHOR) author: String): List<ArtObject>
}
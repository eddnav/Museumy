package com.eddnav.museumy.data.remote

import com.eddnav.museumy.data.remote.model.CollectionResponse
import retrofit2.http.GET


interface RijksDataService {

    // TODO: Handle {culture} later? maybe by modifying the url with an interceptor.

    /**
     * Sorted by primary author.
     */
    @GET("nl/collection?s=artist")
    suspend fun collection(): CollectionResponse
}
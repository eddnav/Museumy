package com.eddnav.museumy.data.remote

import com.eddnav.museumy.data.remote.model.CollectionResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.eddnav.museumy.data.remote.RijksDataServiceParams.PAGE_INDEX
import com.eddnav.museumy.data.remote.RijksDataServiceParams.PAGE_SIZE


interface RijksDataService {

    // TODO: Handle {culture} later? maybe by modifying the url with an interceptor.

    /**
     * Sorted by primary author.
     */
    @GET("nl/collection?s=artist")
    suspend fun collection(
        @Query(PAGE_INDEX) pageIndex: Int,
        @Query(PAGE_SIZE) pageSize: Int
    ): CollectionResponse
}
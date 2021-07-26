package com.eddnav.museumy.data.remote

import com.eddnav.museumy.data.remote.RijksDataServiceParams.PAGE_INDEX
import com.eddnav.museumy.data.remote.RijksDataServiceParams.PAGE_SIZE
import com.eddnav.museumy.data.remote.model.Artwork
import com.eddnav.museumy.data.remote.model.ArtworkResponse
import com.eddnav.museumy.data.remote.model.CollectionResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RijksDataService {

    // TODO: Handle {culture} later? maybe by modifying the url with an interceptor.

    /**
     * Sorted by author, only returns top pieces with images.
     */
    @GET("nl/collection?imgonly=true&toppieces=true&s=artist")
    suspend fun collection(
        @Query(PAGE_INDEX) pageIndex: Int,
        @Query(PAGE_SIZE) pageSize: Int
    ): CollectionResponse

    @GET("nl/collection/{identifier}")
    suspend fun artwork(
        @Path("identifier") identifier: String,
    ): ArtworkResponse
}
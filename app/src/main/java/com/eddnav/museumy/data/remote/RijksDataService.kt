package com.eddnav.museumy.data.remote

import com.eddnav.museumy.data.remote.model.ArtObject
import retrofit2.http.GET
import retrofit2.http.Path

interface RijksDataService {

    @GET("{culture}/collection")
    fun collection(@Path("culture") language: String): List<ArtObject>
}
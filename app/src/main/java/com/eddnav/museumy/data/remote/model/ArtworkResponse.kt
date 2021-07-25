package com.eddnav.museumy.data.remote.model

import com.squareup.moshi.Json

data class ArtworkResponse(
    @Json(name = "artObject")
    val artwork: Artwork
)
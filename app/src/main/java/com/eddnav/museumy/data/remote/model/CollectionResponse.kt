package com.eddnav.museumy.data.remote.model

import com.squareup.moshi.Json

data class CollectionResponse(
    @Json(name = "artObjects")
    val artworks: List<Artwork>
)
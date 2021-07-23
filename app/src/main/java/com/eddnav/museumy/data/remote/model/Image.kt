package com.eddnav.museumy.data.remote.model

import com.squareup.moshi.Json

data class Image(
    // Somehow this can be null, but also the outer object?
    @Json(name = "url")
    val url: String?
)

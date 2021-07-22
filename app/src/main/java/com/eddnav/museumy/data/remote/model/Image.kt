package com.eddnav.museumy.data.remote.model

import com.squareup.moshi.Json

data class Image(
    @Json(name = "url")
    val url: String
)

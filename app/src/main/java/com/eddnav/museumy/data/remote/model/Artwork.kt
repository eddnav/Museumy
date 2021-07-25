package com.eddnav.museumy.data.remote.model

import com.squareup.moshi.Json

data class Artwork(
    @Json(name = "objectNumber")
    val identifier: String,
    @Json(name = "longTitle")
    val title: String,
    @Json(name = "subTitle")
    val subtitle: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "webImage")
    val image: Image?,
    @Json(name = "physicalMedium")
    val physicalMedium: String,
)
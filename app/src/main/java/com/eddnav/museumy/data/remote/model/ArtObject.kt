package com.eddnav.museumy.data.remote.model

import com.squareup.moshi.Json


data class ArtObject(
    @Json(name = "objectNumber")
    val objectNumber: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "webImage")
    val image: Image,
    @Json(name = "principalOrFirstMaker")
    val primaryAuthor: String
)
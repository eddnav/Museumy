package com.eddnav.museumy.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object MoshiFactory {

    fun build() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}
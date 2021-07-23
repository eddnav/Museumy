package com.eddnav.museumy.repository

import com.eddnav.museumy.data.remote.RijksDataService
import com.eddnav.museumy.domain.model.ArtObject
import com.eddnav.museumy.repository.conversion.toDomain

class ArtObjectRepository(private val rijksDataService: RijksDataService) {

     suspend fun getArtObjects(): ArtObject {
         rijksDataService.collection()
             .artObjects
             .map {
                 it.toDomain()
             }
     }
}
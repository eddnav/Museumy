package com.eddnav.museumy.repository

import com.eddnav.museumy.data.remote.RijksDataService
import com.eddnav.museumy.repository.conversion.toDomain

class ArtworkRepository(private val rijksDataService: RijksDataService) {

    suspend fun getArtwork(identifier: String) =
        rijksDataService.artwork(identifier).artwork.toDomain()
}
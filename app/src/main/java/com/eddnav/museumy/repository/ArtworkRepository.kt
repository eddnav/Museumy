package com.eddnav.museumy.repository

import com.eddnav.museumy.data.remote.RijksDataService
import com.eddnav.museumy.domain.model.Artwork
import com.eddnav.museumy.repository.conversion.toDomain

class ArtworkRepository(private val rijksDataService: RijksDataService) {

    suspend fun getArtworks(): List<Artwork> =
        rijksDataService.collection()
            .artworks
            .map {
                it.toDomain()
            }
}
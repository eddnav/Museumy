package com.eddnav.museumy.repository

import com.eddnav.museumy.data.remote.RijksDataService
import com.eddnav.museumy.domain.model.Artwork
import com.eddnav.museumy.repository.conversion.toDomain

class ArtworkRepository(private val rijksDataService: RijksDataService) {

    suspend fun getArtworks(page: Int, pageSize: Int): List<Artwork> =
        rijksDataService.collection(page, pageSize)
            .artworks
            .map {
                it.toDomain()
            }
}
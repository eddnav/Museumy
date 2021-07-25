package com.eddnav.museumy.usecase

import com.eddnav.museumy.repository.ArtworkRepository

class GetArtwork(private val artworkRepository: ArtworkRepository) {

    suspend operator fun invoke(identifier: String) =
        artworkRepository.getArtwork(identifier)
}
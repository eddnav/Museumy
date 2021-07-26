package com.eddnav.museumy

import com.eddnav.museumy.data.remote.model.Image
import com.eddnav.museumy.domain.model.Artwork
import com.eddnav.museumy.repository.conversion.toDomain
import com.eddnav.museumy.data.remote.model.Artwork as RemoteArtwork
import org.junit.Test

import org.junit.Assert.*


class ArtworkModelConversionTest {

    @Test
    fun artwork_toDomainConversionIsCorrect() {
        val identifier = "An Identifier"
        val title = "A title"
        val subtitle = "A subtitle"
        val description = "Some description"
        val imageUrl = "https://museumy.com/image.png"

        val physicalMedium = "Digital art"

        val remoteArtwork = RemoteArtwork(
            identifier, title, subtitle, description, Image(imageUrl), physicalMedium
        )
        val expectedArtwork = Artwork(
            identifier, title, subtitle, description, imageUrl, physicalMedium
        )

        val artwork = remoteArtwork.toDomain()

        assertEquals(expectedArtwork, artwork)
    }
}
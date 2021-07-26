package com.eddnav.museumy

import com.eddnav.museumy.data.remote.model.CollectionEntry as RemoteCollectionEntry
import com.eddnav.museumy.domain.model.CollectionEntry
import com.eddnav.museumy.data.remote.model.Image
import com.eddnav.museumy.repository.conversion.toDomain
import org.junit.Assert
import org.junit.Test

class CollectionEntryModelConversionTest {

    @Test
    fun collectionEntry_toDomainConversionIsCorrect() {
        val identifier = "An Identifier"
        val title = "A title"
        val imageUrl = "https://museumy.com/image.png"
        val primaryAuthor = "A talented fellow"

        val remoteCollectionEntry = RemoteCollectionEntry(
            identifier, title, Image(imageUrl), primaryAuthor
        )
        val expectedCollectionEntry = CollectionEntry(
            identifier, title, imageUrl, primaryAuthor
        )

        val collectionEntry = remoteCollectionEntry.toDomain()

        Assert.assertEquals(expectedCollectionEntry, collectionEntry)
    }
}
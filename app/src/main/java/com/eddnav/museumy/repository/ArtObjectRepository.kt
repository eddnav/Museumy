package com.eddnav.museumy.repository

import com.eddnav.museumy.data.remote.RijksDataService
import com.eddnav.museumy.domain.model.ArtObject
import com.eddnav.museumy.repository.conversion.toDomain

class ArtObjectRepository(private val rijksDataService: RijksDataService) {

    private val primaryAuthors = linkedSetOf<String>()

    suspend fun getGroupedArtObjects(): Map<String, List<ArtObject>> {
        fetchPrimaryAuthors()
        return primaryAuthors.associateWith { author ->
            rijksDataService.collection(author)
                .artObjects
                .map { it.toDomain() }
        }
    }

    private suspend fun fetchPrimaryAuthors() {
        rijksDataService.collection()
            .artObjects
            .mapTo(primaryAuthors) {
                it.primaryAuthor
            }
    }
}
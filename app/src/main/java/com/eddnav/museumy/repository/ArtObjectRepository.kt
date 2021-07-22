package com.eddnav.museumy.repository

import com.eddnav.museumy.data.remote.RijksDataService
import com.eddnav.museumy.domain.model.ArtObject
import com.eddnav.museumy.repository.conversion.toDomain

class ArtObjectRepository(private val rijksDataService: RijksDataService) {

    private val primaryAuthors = linkedSetOf<String>()

    private suspend fun getGroupedArtObjects(): Map<String, List<ArtObject>> {
        fetchPrimaryAuthors()
        return primaryAuthors.associateWith { author ->
            rijksDataService.collection(author)
                .map { it.toDomain() }
        }
    }

    private suspend fun fetchPrimaryAuthors() {
        rijksDataService.collection()
            .mapTo(primaryAuthors) {
                it.primaryAuthor
            }
    }
}
package com.eddnav.museumy.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eddnav.museumy.data.remote.RijksDataService
import com.eddnav.museumy.domain.model.CollectionEntry
import com.eddnav.museumy.repository.conversion.toDomain

class CollectionEntryPagingSource(
    private val rijksDataService: RijksDataService
) : PagingSource<Int, CollectionEntry>() {

    override fun getRefreshKey(state: PagingState<Int, CollectionEntry>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CollectionEntry> {
        try {
            val page = params.key ?: INITIAL_PAGE
            val entries = getCollectionEntries(page, PAGE_SIZE)

            val lastPage = entries.size < PAGE_SIZE
            val previousPage = if (page == INITIAL_PAGE) null else page - 1
            val nextPage = if (lastPage) {
                null
            } else {
                page + 1
            }

            return LoadResult.Page(
                data = entries,
                prevKey = previousPage,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private suspend fun getCollectionEntries(page: Int, pageSize: Int): List<CollectionEntry> =
        rijksDataService.collection(page, pageSize)
            .collectionEntries
            .map {
                it.toDomain()
            }

    companion object {

        // API docs say the pages are 0-indexed but page 0 and 1 for the same page size
        // return the same items.
        private const val INITIAL_PAGE = 1

        /**
         *  No offset based pagination means that we cannot make requests of varying sizes,
         *   otherwise we run the risk of having duplicate items, thus we ignore
         *   [PagingSource.LoadParams.loadSize]
         */
        private const val PAGE_SIZE = 10
    }
}
package com.eddnav.museumy.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.eddnav.museumy.data.remote.RijksDataService
import com.eddnav.museumy.repository.paging.CollectionEntryPagingSource

class CollectionEntryRepository(private val rijksDataService: RijksDataService) {

   /**
    * This value is not used, [CollectionEntryPagingSource] ignores
    * [androidx.paging.PagingSource.LoadParams.loadSize] due to the nature of the API's
    * pagination mechanism.
    */
   private val pagingConfig = PagingConfig(pageSize = 10)

   fun getCollectionEntryPager() = Pager(
      config = pagingConfig,
      pagingSourceFactory = { CollectionEntryPagingSource(rijksDataService) }
   )
}
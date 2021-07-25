package com.eddnav.museumy.feature.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.eddnav.museumy.usecase.GetCollectionItemPagingDataFlow

class CollectionViewModel(
    getCollectionItemPagingDataFlow: GetCollectionItemPagingDataFlow
) : ViewModel() {

    val collectionItemPagingDataFlow = getCollectionItemPagingDataFlow()
        .cachedIn(viewModelScope)
}
package com.eddnav.museumy.di

import com.eddnav.museumy.data.MoshiFactory
import com.eddnav.museumy.data.remote.RijksDataServiceFactory
import com.eddnav.museumy.feature.collection.CollectionViewModel
import com.eddnav.museumy.repository.CollectionEntryRepository
import com.eddnav.museumy.usecase.GetCollectionItemPagingDataFlow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object Koin {

    val modules: List<Module>
        get() = listOf(
            util,
            rijksDataModule,
            repositoryModule,
            useCaseModule,
            viewModelModule
        )

    private val util = module {
        single { MoshiFactory.build() }
    }

    private val rijksDataModule = module {
        single { RijksDataServiceFactory.build(get()) }
    }

    private val repositoryModule = module {
        single { CollectionEntryRepository(get()) }
    }

    private val useCaseModule = module {
        factory { GetCollectionItemPagingDataFlow(get()) }
    }

    private val viewModelModule = module {
        viewModel { CollectionViewModel(get()) }
    }
}
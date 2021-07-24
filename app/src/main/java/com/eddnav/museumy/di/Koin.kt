package com.eddnav.museumy.di

import com.eddnav.museumy.data.MoshiFactory
import com.eddnav.museumy.data.remote.RijksDataServiceFactory
import com.eddnav.museumy.repository.ArtworkRepository
import com.eddnav.museumy.usecase.GetGalleryItemPagingDataFlow
import org.koin.core.module.Module
import org.koin.dsl.module

object Koin {

    val modules: List<Module>
        get() = listOf(
            util,
            rijksDataModule,
            repositoryModule,
            useCaseModule
        )

    private val util = module {
        single { MoshiFactory.build() }
    }

    private val rijksDataModule = module {
        single { RijksDataServiceFactory.build(get()) }
    }

    private val repositoryModule = module {
        single { ArtworkRepository(get()) }
    }

    private val useCaseModule = module {
        single { GetGalleryItemPagingDataFlow(get()) }
    }
}
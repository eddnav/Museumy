package com.eddnav.museumy.di

import com.eddnav.museumy.data.MoshiFactory
import com.eddnav.museumy.data.remote.RijksDataServiceFactory
import org.koin.core.module.Module
import org.koin.dsl.module

object Koin {

    val modules: List<Module>
        get() = listOf(
            util,
            rijksDataModule
        )

    private val util = module {
        single { MoshiFactory.build() }
    }

    private val rijksDataModule = module {
        single { RijksDataServiceFactory.build(get()) }
    }
}
package org.example.project.cmp.common.DI

import org.example.project.cmp.common.MainViewModel
import org.example.project.cmp.common.storage.AppStorage
import org.example.project.cmp.common.storage.database.AgenciesDao
import org.example.project.cmp.common.storage.database.AppDatabase
import org.example.project.cmp.common.storage.database.getAppDatabase
import org.example.project.cmp.feature.login.LoginViewModel
import org.example.project.cmp.feature.main.data.AgenciesRepository
import org.example.project.cmp.feature.main.data.AgenciesRepositoryImpl
import org.example.project.cmp.feature.main.data.net.ApiInteraction
import org.example.project.cmp.feature.main.data.net.createHttpClient
import org.example.project.cmp.feature.main.presentation.MainAgencyViewModel
import org.example.project.createDataStore
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(presentationModule, domainModule, networkingModule, dataModule, mainViewModelModule)
    }
}
val mainViewModelModule = module {
    viewModelOf(::MainViewModel)
}

val domainModule = module {
    single<AgenciesRepository> {
        AgenciesRepositoryImpl(
            api = get(),
            agencyDao = get<AgenciesDao>()
        )
    }
}

val dataModule = module {
    single<AppDatabase>{ getAppDatabase() }
    single<AgenciesDao> { get<AppDatabase>().agencyDao() }
    single { createDataStore() }
    single { AppStorage(dataStorage = get()) }
}

val presentationModule = module {
    viewModelOf(::MainAgencyViewModel)
    viewModelOf(::LoginViewModel)
}

val networkingModule = module {
    single { createHttpClient() }
    single {
        ApiInteraction(
            url = "https://ll.thespacedevs.com/2.3.0/",
            httpClient = get()
        )
    }
}


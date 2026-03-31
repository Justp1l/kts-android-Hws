package org.example.spaceShape.cmp.common.DI

import org.example.spaceShape.cmp.feature.onBoard.domain.OnboardingViewModel
import org.example.spaceShape.cmp.feature.onBoard.data.AppStorage
import org.example.spaceShape.cmp.feature.main.agencies.data.database.AgenciesDao
import org.example.spaceShape.cmp.feature.main.agencies.data.database.MainAgencyDatabase
import org.example.spaceShape.cmp.feature.main.agencies.data.database.getAppDatabase
import org.example.spaceShape.cmp.feature.login.domain.LoginViewModel
import org.example.spaceShape.cmp.feature.main.agencies.data.AgenciesRepository
import org.example.spaceShape.cmp.feature.main.agencies.data.AgenciesRepositoryImpl
import org.example.spaceShape.cmp.common.net.ApiInteraction
import org.example.spaceShape.cmp.common.net.createHttpClient
import org.example.spaceShape.cmp.feature.main.agencies.domain.MainAgencyViewModel
import org.example.spaceShape.cmp.feature.main.domain.MainViewModel
import org.example.spaceShape.createDataStore
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(domainModule, networkingModule, dataModule, onboardingViewModelModule, mainAgencyModule, loginModule, mainVMModule)
    }
}
val onboardingViewModelModule = module {
    viewModelOf(::OnboardingViewModel)
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
    single<MainAgencyDatabase>{ getAppDatabase() }
    single<AgenciesDao> { get<MainAgencyDatabase>().agencyDao() }
    single { createDataStore() }
    single { AppStorage(dataStorage = get()) }
}

val mainAgencyModule = module {
    viewModelOf(::MainAgencyViewModel)
}

val loginModule = module {
    viewModelOf(::LoginViewModel)
}
val mainVMModule = module {
    viewModelOf(::MainViewModel)
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


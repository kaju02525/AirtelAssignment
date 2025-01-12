package com.airtel.di

import com.airtel.mvvm.MainViewModel
import com.airtel.network.RestClient
import com.airtel.network.RestClient.webServices
import com.airtel.repository.Repository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // a single instance for Repository class
    single { Repository(get()) }
}

val networkModule = module {
    single { RestClient }
    single { webServices() }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}
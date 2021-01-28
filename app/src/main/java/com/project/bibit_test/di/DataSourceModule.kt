package com.project.bibit_test.di

import com.project.bibit_test.data.AppExecutors
import com.project.bibit_test.data.source.local.sharedpref.SharedPrefDataStore
import com.project.bibit_test.data.source.remote.RemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { RemoteDataSource(get()) }
}

val appExecutorModule = module {
    single { AppExecutors() }
}

val sharedPrefModule = module {
    single {
        SharedPrefDataStore.getInstance(androidContext())
    }
}

val dataSourceModuleList = mutableListOf(
    remoteDataSourceModule,
    appExecutorModule,
    sharedPrefModule
)
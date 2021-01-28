package com.project.bibit_test.di

import com.project.bibit_test.data.repository.CoinRepository
import com.project.bibit_test.data.repository.LoginRepository
import com.project.bibit_test.data.repository.LogoutRepository
import com.project.bibit_test.data.repository.ProfileRepository
import com.project.bibit_test.domain.repository.ICoinRepository
import com.project.bibit_test.domain.repository.ILoginRepository
import com.project.bibit_test.domain.repository.ILogoutRepository
import com.project.bibit_test.domain.repository.IProfileRepository
import org.koin.dsl.module

private val coinRepositoryModule = module {
    single<ICoinRepository> { CoinRepository(get(), get()) }
}

private val loginRepositoryModule = module {
    single<ILoginRepository> { LoginRepository(get()) }
}

private val logoutRepositoryModule = module {
    single<ILogoutRepository> { LogoutRepository(get()) }
}

private val profileRepositoryModule = module {
    single<IProfileRepository> { ProfileRepository(get()) }
}

val repositoryModuleList = mutableListOf(
    coinRepositoryModule,
    loginRepositoryModule,
    logoutRepositoryModule,
    profileRepositoryModule
)
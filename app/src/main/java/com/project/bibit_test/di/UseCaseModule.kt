package com.project.bibit_test.di

import com.project.bibit_test.domain.usecase.coin.CoinInteractor
import com.project.bibit_test.domain.usecase.coin.CoinUseCase
import com.project.bibit_test.domain.usecase.login.LoginInteractor
import com.project.bibit_test.domain.usecase.login.LoginUseCase
import com.project.bibit_test.domain.usecase.logout.LogoutInteractor
import com.project.bibit_test.domain.usecase.logout.LogoutUseCase
import com.project.bibit_test.domain.usecase.profile.ProfileInteractor
import com.project.bibit_test.domain.usecase.profile.ProfileUseCase
import org.koin.dsl.module

private val coinUseCaseModule = module {
    single<CoinUseCase> {
        CoinInteractor(coinRepository = get())
    }
}

private val loginUseCaseModule = module {
    single<LoginUseCase> {
        LoginInteractor(loginRepository = get())
    }
}

private val logoutUseCaseModule = module {
    single<LogoutUseCase> {
        LogoutInteractor(logoutRepository = get())
    }
}

private val profileUseCaseModule = module {
    single<ProfileUseCase> {
        ProfileInteractor(profileRepository = get())
    }
}

val useCaseModuleList = mutableListOf(
    coinUseCaseModule,
    loginUseCaseModule,
    logoutUseCaseModule,
    profileUseCaseModule
)
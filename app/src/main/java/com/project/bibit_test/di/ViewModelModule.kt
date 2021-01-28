package com.project.bibit_test.di

import com.project.bibit_test.ui.chat.ChatViewModel
import com.project.bibit_test.ui.home.HomeViewModel
import com.project.bibit_test.ui.login.LoginViewModel
import com.project.bibit_test.ui.logout.LogoutViewModel
import com.project.bibit_test.ui.main.MainViewModel
import com.project.bibit_test.ui.portfolio.PortfolioViewModel
import com.project.bibit_test.ui.search.SearchViewModel
import com.project.bibit_test.ui.splash.SplashViewModel
import com.project.bibit_test.ui.stream.StreamViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val streamViewModelModule = module {
    viewModel { StreamViewModel() }
}

private val searchViewModelModule = module {
    viewModel { SearchViewModel() }
}

private val chatViewModelModule = module {
    viewModel { ChatViewModel() }
}

private val portfolioViewModelModule = module {
    viewModel { PortfolioViewModel() }
}

private val homeViewModelModule = module {
    viewModel { HomeViewModel(coinUseCase = get()) }
}

private val loginViewModelModule = module {
    viewModel { LoginViewModel(loginUseCase = get()) }
}

private val splashViewModelModule = module {
    viewModel { SplashViewModel(loginUseCase = get()) }
}

private val profileViewModelModule = module {
    viewModel { MainViewModel(profileUseCase = get()) }
}

private val logoutViewModelModule = module {
    viewModel { LogoutViewModel(logoutUseCase = get()) }
}

val viewModelModuleList = mutableListOf(
    streamViewModelModule,
    searchViewModelModule,
    chatViewModelModule,
    portfolioViewModelModule,
    homeViewModelModule,
    loginViewModelModule,
    splashViewModelModule,
    profileViewModelModule,
    logoutViewModelModule
)
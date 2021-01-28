package com.project.bibit_test.ui.splash

import androidx.lifecycle.ViewModel
import com.project.bibit_test.domain.usecase.login.LoginUseCase

class SplashViewModel constructor(private val loginUseCase: LoginUseCase): ViewModel(){
    val isUserAlreadyLogin by lazy { loginUseCase.isUserAlreadyLogin() }
}
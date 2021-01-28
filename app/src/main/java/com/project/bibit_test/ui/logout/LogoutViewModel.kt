package com.project.bibit_test.ui.logout

import androidx.lifecycle.ViewModel
import com.project.bibit_test.domain.usecase.logout.LogoutUseCase

class LogoutViewModel constructor(private val logoutUseCase: LogoutUseCase): ViewModel() {

    fun logout(){
        logoutUseCase.logout()
    }

}
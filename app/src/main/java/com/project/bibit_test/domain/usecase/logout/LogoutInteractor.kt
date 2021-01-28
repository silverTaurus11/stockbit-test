package com.project.bibit_test.domain.usecase.logout

import com.project.bibit_test.domain.repository.ILogoutRepository

class LogoutInteractor constructor(private val logoutRepository: ILogoutRepository): LogoutUseCase {

    override fun logout() {
        logoutRepository.logout()
    }
}
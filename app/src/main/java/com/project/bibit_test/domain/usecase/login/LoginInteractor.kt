package com.project.bibit_test.domain.usecase.login

import androidx.lifecycle.LiveData
import com.project.bibit_test.data.source.local.sharedpref.model.ProfileItem
import com.project.bibit_test.domain.repository.ILoginRepository

class LoginInteractor constructor(private val loginRepository: ILoginRepository): LoginUseCase {

    override fun isUserAlreadyLogin(): LiveData<Boolean> {
        return loginRepository.isUserAlreadyLogin()
    }

    override fun doLogin(item: ProfileItem): LiveData<Boolean> {
        return loginRepository.doLogin(item)
    }
}
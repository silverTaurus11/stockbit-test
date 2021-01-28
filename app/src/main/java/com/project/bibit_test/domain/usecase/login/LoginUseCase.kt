package com.project.bibit_test.domain.usecase.login

import androidx.lifecycle.LiveData
import com.project.bibit_test.data.source.local.sharedpref.model.ProfileItem

interface LoginUseCase {
    fun isUserAlreadyLogin(): LiveData<Boolean>
    fun doLogin(item: ProfileItem): LiveData<Boolean>
}
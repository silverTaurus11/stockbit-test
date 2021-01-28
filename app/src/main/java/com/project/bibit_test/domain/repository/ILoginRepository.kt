package com.project.bibit_test.domain.repository

import androidx.lifecycle.LiveData
import com.project.bibit_test.data.source.local.sharedpref.model.ProfileItem

interface ILoginRepository {
    fun doLogin(item: ProfileItem): LiveData<Boolean>
    fun isUserAlreadyLogin(): LiveData<Boolean>
}
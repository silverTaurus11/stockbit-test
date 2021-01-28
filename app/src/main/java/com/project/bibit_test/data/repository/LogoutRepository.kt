package com.project.bibit_test.data.repository

import com.project.bibit_test.data.source.local.sharedpref.IS_REMEMBER_LOGIN
import com.project.bibit_test.data.source.local.sharedpref.SharedPrefDataStore
import com.project.bibit_test.domain.repository.ILogoutRepository

class LogoutRepository constructor(
        private val sharedPrefDataStore: SharedPrefDataStore): ILogoutRepository {

    override fun logout() {
        sharedPrefDataStore.clearMyProfile()
        sharedPrefDataStore.saveData(IS_REMEMBER_LOGIN, false)
    }

}
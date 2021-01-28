package com.project.bibit_test.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.bibit_test.data.source.local.sharedpref.IS_REMEMBER_LOGIN
import com.project.bibit_test.data.source.local.sharedpref.SharedPrefDataStore
import com.project.bibit_test.data.source.local.sharedpref.model.ProfileItem
import com.project.bibit_test.domain.repository.ILoginRepository

class LoginRepository constructor(
        private val sharedPrefDataStore: SharedPrefDataStore): ILoginRepository {

    override fun doLogin(item: ProfileItem): LiveData<Boolean> {
        val isLoginSuccess = MutableLiveData<Boolean>()
        try{
            sharedPrefDataStore.saveMyProfile(item)
            sharedPrefDataStore.saveData(IS_REMEMBER_LOGIN, true)
            isLoginSuccess.value = true
        } catch (e: Exception){
            isLoginSuccess.value = false
        }
        return isLoginSuccess
    }

    override fun isUserAlreadyLogin(): LiveData<Boolean> {
        return sharedPrefDataStore.getBooleanLiveData(IS_REMEMBER_LOGIN)
    }
}
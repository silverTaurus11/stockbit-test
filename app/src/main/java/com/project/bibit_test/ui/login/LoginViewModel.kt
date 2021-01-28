package com.project.bibit_test.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.bibit_test.Utils
import com.project.bibit_test.data.source.local.sharedpref.model.ProfileItem
import com.project.bibit_test.domain.usecase.login.LoginUseCase

class LoginViewModel constructor(private val loginUseCase: LoginUseCase): ViewModel() {

    enum class Validation{
        USERNAME_INVALID,
        PASSWORD_INVALID,
        NONE
    }

    val formValidation = MutableLiveData<Validation>()

    init {
        formValidation.value = Validation.NONE
    }

    fun doLogin(profileItem: ProfileItem): LiveData<Boolean> {
        val isSuccess = MutableLiveData<Boolean>()
        doFormValidation(profileItem).also {
            if(it){
                return loginUseCase.doLogin(profileItem)
            } else{
                isSuccess.value = false
            }
        }
        return isSuccess
    }

    val isUserAlreadyLogin by lazy { loginUseCase.isUserAlreadyLogin() }

    private fun doFormValidation(profileItem: ProfileItem): Boolean{
        val isUsernameValid = Utils.isUserNameValid(profileItem.userName)
        if(!isUsernameValid) formValidation.value = Validation.USERNAME_INVALID
        val isPasswordValid = Utils.isUserPasswordValid(profileItem.password)
        if(!isPasswordValid) formValidation.value = Validation.PASSWORD_INVALID
        return isUsernameValid && isPasswordValid
    }

}
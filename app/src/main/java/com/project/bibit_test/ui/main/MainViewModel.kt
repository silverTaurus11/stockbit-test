package com.project.bibit_test.ui.main

import androidx.lifecycle.ViewModel
import com.project.bibit_test.domain.usecase.profile.ProfileUseCase

class MainViewModel constructor(private val profileUseCase: ProfileUseCase): ViewModel() {

    val myProfile by lazy { profileUseCase.getProfile() }
}
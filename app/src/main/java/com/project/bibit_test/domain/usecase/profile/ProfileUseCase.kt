package com.project.bibit_test.domain.usecase.profile

import androidx.lifecycle.LiveData
import com.project.bibit_test.data.source.local.sharedpref.model.ProfileItem

interface ProfileUseCase {
    fun getProfile(): LiveData<ProfileItem?>
}
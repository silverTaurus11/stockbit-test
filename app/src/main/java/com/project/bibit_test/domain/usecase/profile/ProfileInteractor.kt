package com.project.bibit_test.domain.usecase.profile

import androidx.lifecycle.LiveData
import com.project.bibit_test.data.source.local.sharedpref.model.ProfileItem
import com.project.bibit_test.domain.repository.IProfileRepository

class ProfileInteractor constructor(private val profileRepository: IProfileRepository): ProfileUseCase {
    override fun getProfile(): LiveData<ProfileItem?> {
        return profileRepository.getProfile()
    }
}
package com.project.bibit_test.data.repository

import androidx.lifecycle.LiveData
import com.project.bibit_test.data.source.local.sharedpref.SharedPrefDataStore
import com.project.bibit_test.data.source.local.sharedpref.model.ProfileItem
import com.project.bibit_test.domain.repository.IProfileRepository

class ProfileRepository constructor(private val sharedPrefDataStore: SharedPrefDataStore): IProfileRepository {
    override fun getProfile(): LiveData<ProfileItem?> {
        return sharedPrefDataStore.getMyProfile()
    }
}
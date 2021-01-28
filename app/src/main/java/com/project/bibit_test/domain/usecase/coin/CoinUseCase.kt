package com.project.bibit_test.domain.usecase.coin

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.project.bibit_test.data.Resource
import com.project.bibit_test.data.source.local.sharedpref.model.ProfileItem
import com.project.bibit_test.data.source.remote.model.CoinItem

interface CoinUseCase {
    fun getTopTierVolumeFull(): LiveData<Resource<PagedList<CoinItem>>>
}
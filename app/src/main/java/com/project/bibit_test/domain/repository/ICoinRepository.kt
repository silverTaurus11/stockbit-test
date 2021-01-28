package com.project.bibit_test.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.project.bibit_test.data.Resource
import com.project.bibit_test.data.source.remote.model.CoinItem

interface ICoinRepository {
    fun getTopTierVolumeFull(): LiveData<Resource<PagedList<CoinItem>>>
}
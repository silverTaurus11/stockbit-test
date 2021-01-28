package com.project.bibit_test.domain.usecase.coin

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.project.bibit_test.data.Resource
import com.project.bibit_test.data.source.remote.model.CoinItem
import com.project.bibit_test.domain.repository.ICoinRepository

class CoinInteractor constructor(private val coinRepository: ICoinRepository): CoinUseCase {

    override fun getTopTierVolumeFull(): LiveData<Resource<PagedList<CoinItem>>> {
        return coinRepository.getTopTierVolumeFull()
    }

}
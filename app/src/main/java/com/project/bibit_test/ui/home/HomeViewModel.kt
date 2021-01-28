package com.project.bibit_test.ui.home

import androidx.lifecycle.ViewModel
import com.project.bibit_test.domain.usecase.coin.CoinUseCase
import com.project.bibit_test.ui.RefreshableLiveData

class HomeViewModel constructor(private val coinUseCase: CoinUseCase): ViewModel() {

    val topTierVolumeFull by lazy { RefreshableLiveData{ coinUseCase.getTopTierVolumeFull()} }

}
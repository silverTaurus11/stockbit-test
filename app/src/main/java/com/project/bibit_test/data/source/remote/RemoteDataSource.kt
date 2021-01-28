package com.project.bibit_test.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.project.bibit_test.data.source.remote.model.CoinResponse
import com.project.bibit_test.data.source.remote.network.ApiResponse
import com.project.bibit_test.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor(private val apiService: ApiService) {

    fun getTopTierVolumeFull(page: Int = 1): LiveData<ApiResponse<CoinResponse>>{
        val config = mutableMapOf<String, Any>(
            "limit" to 10,
            "page" to page,
            "tsym" to "USD"
        )
        return flow {
            try {
                val response = apiService.getTopCoins(config)
                val coinList = response.data
                if (coinList.isNullOrEmpty()){
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(response))
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO).asLiveData()
    }
}
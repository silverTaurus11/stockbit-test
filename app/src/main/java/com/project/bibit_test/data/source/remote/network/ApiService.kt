package com.project.bibit_test.data.source.remote.network

import com.project.bibit_test.data.source.remote.model.CoinResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

@JvmSuppressWildcards
interface ApiService {
    @GET("data/top/totaltoptiervolfull")
    suspend fun getTopCoins(@QueryMap data: Map<String, Any>): CoinResponse
}
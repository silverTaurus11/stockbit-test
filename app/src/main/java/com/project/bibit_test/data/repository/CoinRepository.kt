package com.project.bibit_test.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.project.bibit_test.data.AppExecutors
import com.project.bibit_test.data.DictionaryDataFactory
import com.project.bibit_test.data.OnlyNetworkBoundResourceLiveData
import com.project.bibit_test.data.Resource
import com.project.bibit_test.data.source.remote.RemoteDataSource
import com.project.bibit_test.data.source.remote.model.CoinItem
import com.project.bibit_test.data.source.remote.model.CoinResponse
import com.project.bibit_test.data.source.remote.network.ApiResponse
import com.project.bibit_test.domain.repository.ICoinRepository

class CoinRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
): ICoinRepository {

    override fun getTopTierVolumeFull(): LiveData<Resource<PagedList<CoinItem>>> {
        var nextPage = 1
        var networkBound: OnlyNetworkBoundResourceLiveData<PagedList<CoinItem>, CoinResponse>?= null
        val dataFactory = DictionaryDataFactory(mutableListOf(),
            object : DictionaryDataFactory.CallBackListener<CoinItem>{
                override fun callBackAfter(
                    params: PageKeyedDataSource.LoadParams<Int>,
                    callback: PageKeyedDataSource.LoadCallback<Int, CoinItem>
                ) {
                    appExecutors.mainThread().execute {
                        if(nextPage <= 5){
                            networkBound?.fetchFromNetwork()
                        }
                    }
                }

                override fun callBackBefore(
                    params: PageKeyedDataSource.LoadParams<Int>,
                    callback: PageKeyedDataSource.LoadCallback<Int, CoinItem>
                ) {

                }

            })

        networkBound = object :OnlyNetworkBoundResourceLiveData<PagedList<CoinItem>, CoinResponse>(appExecutors){
            override fun createCall(): LiveData<ApiResponse<CoinResponse>> {
                return remoteDataSource.getTopTierVolumeFull(nextPage)
            }

            override fun convertToResultType(remoteData: CoinResponse): LiveData<PagedList<CoinItem>> {
                nextPage += 1
                dataFactory.updateData(remoteData.data?.toMutableList()?: mutableListOf())
                return LivePagedListBuilder(dataFactory, PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()).build()
            }
        }

        return networkBound.asLiveData()
    }
}
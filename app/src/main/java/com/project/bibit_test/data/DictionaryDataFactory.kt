package com.project.bibit_test.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource

/**
 * created by silver taurus
 */
class DictionaryDataFactory<T>(private val data: MutableList<T> = mutableListOf(),
                               private val callBackListener: CallBackListener<T>) : DataSource.Factory<Int, T>() {

    private val liveData = MutableLiveData<PageKeyedDataSource<Int, T>>()

    override fun create(): DataSource<Int, T> {
        val dataSource = DictionaryDataSource(data, callBackListener)
        liveData.postValue(dataSource)
        return dataSource
    }

    fun updateData(data: MutableList<T>){
        this.data.addAll(data)
        val dataSource = DictionaryDataSource(this.data, callBackListener)
        liveData.postValue(dataSource)
    }

    class DictionaryDataSource<T>(private val data: MutableList<T>,
                                  private val callBackListener: CallBackListener<T>) : PageKeyedDataSource<Int, T>() {

        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, T>) {
            callback.onResult(data, null, 1)
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
            callBackListener.callBackAfter(params, callback)
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
            callBackListener.callBackBefore(params, callback)
        }

    }

    interface CallBackListener<T>{
        fun callBackAfter(params: PageKeyedDataSource.LoadParams<Int>,
                          callback: PageKeyedDataSource.LoadCallback<Int, T>)
        fun callBackBefore(params: PageKeyedDataSource.LoadParams<Int>,
                           callback: PageKeyedDataSource.LoadCallback<Int, T>)
    }
}


package com.project.bibit_test.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.project.bibit_test.LiveDataTestUtil.observeForTesting
import com.project.bibit_test.data.repository.LoginRepository
import com.project.bibit_test.data.source.local.sharedpref.LOGIN_VIA_NORMAL
import com.project.bibit_test.data.source.local.sharedpref.SharedPrefDataStore
import com.project.bibit_test.data.source.local.sharedpref.model.ProfileItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class LoginRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val sharedPrefDataStore = Mockito.mock(SharedPrefDataStore::class.java)
    private val loginRepository = LoginRepository(sharedPrefDataStore)

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun cleanUp() {
        Dispatchers.setMain(Dispatchers.Default)
    }

    @Test
    fun doLoginTest(){
        val profileItem = ProfileItem("lalala", "opopopo", LOGIN_VIA_NORMAL)
        val isSuccess = loginRepository.doLogin(profileItem)
        verify(sharedPrefDataStore).saveMyProfile(any())
        verify(sharedPrefDataStore).saveData(ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean())
        isSuccess.observeForTesting {
            Assert.assertEquals(true, isSuccess.value)
        }
    }

    @Test
    fun isUserAlreadyLogin_True(){
        Mockito.`when`(sharedPrefDataStore.getBooleanLiveData(any())).thenReturn(MutableLiveData<Boolean>().apply { value = true })
        val isRemember = loginRepository.isUserAlreadyLogin()
        verify(sharedPrefDataStore).getBooleanLiveData(any())
        isRemember.observeForTesting {
            Assert.assertEquals(true, isRemember.value)
        }
    }

    @Test
    fun isUserAlreadyLogin_False(){
        Mockito.`when`(sharedPrefDataStore.getBooleanLiveData(any())).thenReturn(MutableLiveData<Boolean>().apply { value = false })
        val isRemember = loginRepository.isUserAlreadyLogin()
        verify(sharedPrefDataStore).getBooleanLiveData(any())
        isRemember.observeForTesting {
            Assert.assertEquals(false, isRemember.value)
        }
    }

}
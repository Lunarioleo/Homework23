package com.example.homework23

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito


class MyViewModelTest{
    @get:Rule
    val testRule:TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup(){
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun getSucessResponse(){
        val repository = Mockito.mock(MyRepository::class.java)
        val sucessfulResponse = Bitcoin(Data("etherum", "15000"))
        val viewModel = MyViewModel(repository)
        val eventList = mutableListOf<MyViewModel.UiState>()
        viewModel.Uistate.observeForever {
            eventList.add(it)
        }

        runBlocking {
            Mockito.`when`(repository.getBitcoinCurrency()).thenReturn(sucessfulResponse)
        }
        viewModel.getCurrency()

        Assert.assertEquals(MyViewModel.UiState.NoData, eventList[0])

        val weatherState = eventList[1] as MyViewModel.UiState.Result
        Assert.assertEquals(sucessfulResponse, weatherState.result)
    }

    @Test
    fun getNullResponse(){
        val repository = Mockito.mock(MyRepository::class.java)
        val nullResponse = Bitcoin(null)
        val viewModel = MyViewModel(repository)
        var eventList = mutableListOf<MyViewModel.UiState>()
        viewModel.Uistate.observeForever {
            eventList.add(it)
        }
        runBlocking {
            Mockito.`when`(repository.getBitcoinCurrency()).thenReturn(nullResponse)
        }
        viewModel.getCurrency()

        Assert.assertEquals(MyViewModel.UiState.NoData, eventList[0])
    }

}
package com.example.homework23

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyViewModel @Inject constructor(val repository: MyRepository) :ViewModel() {
    val _uiState = MutableLiveData<UiState>(UiState.NoData)
    var Uistate: LiveData<UiState> = _uiState

    fun getCurrency(){
        viewModelScope.launch {
            val bitcoin = async { repository.getBitcoinCurrency() }.await()
            val body = bitcoin.data
            if (body != null) {
                _uiState.postValue(UiState.Result(bitcoin))
            }
        }
    }

    sealed class UiState{
        object NoData: UiState()
        class Result(val result: Bitcoin?): UiState()
    }
}
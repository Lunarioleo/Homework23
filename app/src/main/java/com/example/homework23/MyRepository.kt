package com.example.homework23

import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Response


class MyRepository (private val myClient: ApiClient){
    suspend fun getBitcoinCurrency(): Bitcoin {
        val apiInterface = myClient.client.create(ApiInterface::class.java)
        return apiInterface.getCurrency()
    }
}
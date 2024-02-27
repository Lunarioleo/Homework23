package com.example.homework23

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class ApiClient @Inject constructor(){
    val client = Retrofit.Builder()
        .baseUrl("https://api.coincap.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()
}
package com.example.homework23

import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {
    @GET ("/v2/rates/bitcoin")
    suspend fun getCurrency(): Bitcoin
}
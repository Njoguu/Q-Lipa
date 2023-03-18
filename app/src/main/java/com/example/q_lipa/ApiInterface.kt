package com.example.q_lipa

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/v2/jobs")
    fun getData(): Call<List<AvailableJob>>
}
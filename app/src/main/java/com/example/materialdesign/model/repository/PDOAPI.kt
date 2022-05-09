package com.example.materialdesign.model.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PDOAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey: String,
        @Query("date") date:String
    ): Call<PDOServerResponse>
}
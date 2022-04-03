package com.example.retrofitapitest.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/current.json")
    fun fetchWeather(@Query("key") apiKey: String, @Query("q") location: String): Call<WeatherApiResponse>
}
package com.example.retrofitapitest.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "WEATHERFETCHER"

class WeatherFetcher {

    private val weatherApi: WeatherApi
    init {
        val retrofit: Retrofit = Retrofit
            .Builder()
            .baseUrl("https://api.weatherapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        weatherApi = retrofit.create(WeatherApi::class.java)
    }

    fun fetchWeather(): LiveData<WeatherItem> {
        val responseLiveData: MutableLiveData<WeatherItem> = MutableLiveData()
        val weatherRequest: Call<WeatherApiResponse> = weatherApi.fetchWeather(ApiKey().apiKey, "Austin")
        weatherRequest.enqueue(object : Callback<WeatherApiResponse> {
            override fun onFailure(call: Call<WeatherApiResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch weather data", t)
            }
            override fun onResponse(
                call: Call<WeatherApiResponse>,
                response: Response<WeatherApiResponse>
            ) {
                Log.d(TAG, "Response received " + response.body().toString())
                //responseLiveData.value = response.body()
                val weatherApiResponse: WeatherApiResponse? = response.body()
                val weatherItem: WeatherItem? = weatherApiResponse?.current
                Log.d(TAG, "temp: " + weatherItem?.temp.toString())
                Log.d(TAG, "wind speed: " + weatherItem?.windSpeed.toString())
                Log.d(TAG, "wind dir: " + weatherItem?.windDir.toString())
                Log.d(TAG, "humidity: " + weatherItem?.humidity.toString())
                /*
                var weatherItems: List<WeatherItem> = dataResponse?.weatherItems
                    ?: mutableListOf()
                weatherItems = weatherItems.filterNot {
                    it.temp.isBlank()
                }*/
                responseLiveData.value = weatherItem



            }
        })
        return responseLiveData
    }
}
package com.example.retrofitapitest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitapitest.api.WeatherFetcher
import com.example.retrofitapitest.api.WeatherItem

class WeatherDataViewModel: ViewModel() {
    val weatherItemLiveData: LiveData<WeatherItem>
    init {
        weatherItemLiveData = WeatherFetcher().fetchWeather()
    }
}
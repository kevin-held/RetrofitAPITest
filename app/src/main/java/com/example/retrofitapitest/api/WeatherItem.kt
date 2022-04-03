package com.example.retrofitapitest.api

import com.google.gson.annotations.SerializedName

data class WeatherItem(
    @SerializedName("temp_f")
    var temp: String="",
    @SerializedName("wind_mph")
    var windSpeed: String="",
    @SerializedName("wind_dir")
    var windDir: String="",
    @SerializedName("humidity")
    var humidity: String=""
) {
    override fun toString(): String {
        return "Temperature: $temp F\n" +
                "Wind Speed: $windSpeed mph\n" +
                "Wind Direction: $windDir \n" +
                "Humidity: $humidity%"
    }
}

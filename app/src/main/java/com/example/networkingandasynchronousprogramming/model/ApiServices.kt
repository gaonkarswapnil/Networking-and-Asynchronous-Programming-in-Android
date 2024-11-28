package com.example.networkingandasynchronousprogramming.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("v1/city")
    fun getCityData(
        @Query("name") name: String
    ): Call<CityResponse>

    @GET("/v1/forecast.json?")
    fun getWeatherData(
        @Query("key") key: String,
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Call<WeatherResponse>
}
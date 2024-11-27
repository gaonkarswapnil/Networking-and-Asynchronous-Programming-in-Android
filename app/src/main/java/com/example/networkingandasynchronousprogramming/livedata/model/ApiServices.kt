package com.example.networkingandasynchronousprogramming.livedata.model

import androidx.lifecycle.LiveData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {


    @GET("v1/city")
    suspend fun getCityData(
        @Query("name") name: String
    ): Response<CityResponse>

    @GET("v1/forecast.json")
    suspend fun getWeatherData(
        @Query("key") key: String,
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Response<WeatherResponse>
}
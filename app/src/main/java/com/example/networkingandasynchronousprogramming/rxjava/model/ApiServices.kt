package com.example.networkingandasynchronousprogramming.rxjava.model

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {


    @GET("v1/city")
    fun getCityData(
        @Query("name") name: String
    ): Observable<CityResponse>

    @GET("v1/forecast.json")
    fun getWeatherData(
        @Query("key") key: String,
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Observable<WeatherResponse>
}
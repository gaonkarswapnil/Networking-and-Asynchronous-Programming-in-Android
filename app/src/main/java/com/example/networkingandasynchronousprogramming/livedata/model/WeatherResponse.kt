package com.example.networkingandasynchronousprogramming.livedata.model

data class WeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)
package com.example.networkingandasynchronousprogramming.rxjava.model

data class WeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)
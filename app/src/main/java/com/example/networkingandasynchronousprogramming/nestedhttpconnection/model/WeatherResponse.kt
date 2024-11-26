package com.example.networkingandasynchronousprogramming.nestedhttpconnection.model

data class WeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)
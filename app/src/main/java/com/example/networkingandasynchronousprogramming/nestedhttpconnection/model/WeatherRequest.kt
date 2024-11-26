package com.example.networkingandasynchronousprogramming.nestedhttpconnection.model

data class WeatherRequest (
    val key: String,
    val q: String,
    val days: Int,
    val aqi: String,
    val alerts: String
)
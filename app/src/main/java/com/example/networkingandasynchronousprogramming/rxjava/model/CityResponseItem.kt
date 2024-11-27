package com.example.networkingandasynchronousprogramming.rxjava.model

data class CityResponseItem(
    val country: String,
    val is_capital: Boolean,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val population: Int,
    val region: String
)
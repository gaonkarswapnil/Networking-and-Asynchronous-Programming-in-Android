package com.example.networkingandasynchronousprogramming.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkingandasynchronousprogramming.model.APIRepository
import com.example.networkingandasynchronousprogramming.model.CityResponse
import com.example.networkingandasynchronousprogramming.model.WeatherRequest
import com.example.networkingandasynchronousprogramming.model.WeatherResponse
import kotlinx.coroutines.launch

class ApiViewModel(
    val application: Application,
    val apiRepository: APIRepository
): ViewModel() {

    fun getCityData(city: String): LiveData<CityResponse> {
//        val cityResponse = apiRepository.getCityData(city)
//
//        viewModelScope.launch {
//            apiRepository.getCityData(city)
//        }
        return apiRepository.getCityData(city)
    }

    fun getWeatherData(weatherRequest: WeatherRequest): LiveData<WeatherResponse> {
//        val weatherResponse = apiRepository.getWeatherData(weatherRequest)
//
//        viewModelScope.launch {
//            apiRepository.getWeatherData(weatherRequest)
//        }
//
//        Log.d("Nested API weather ViewModel", weatherResponse.toString())
        return apiRepository.getWeatherData(weatherRequest)
    }
}
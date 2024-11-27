package com.example.networkingandasynchronousprogramming.livedata.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkingandasynchronousprogramming.livedata.model.APIRepository
import com.example.networkingandasynchronousprogramming.livedata.model.CityResponse
import com.example.networkingandasynchronousprogramming.livedata.model.WeatherRequest
import com.example.networkingandasynchronousprogramming.livedata.model.WeatherResponse
import kotlinx.coroutines.launch
import android.util.Log

class ApiViewModel(
    val application: Application,
    val apiRepository: APIRepository
): ViewModel() {


    fun getCityData(city: String): LiveData<CityResponse>{
        val cityResponse = MutableLiveData<CityResponse>()

        viewModelScope.launch {
            apiRepository.getCityData(city).collect{
                cityResponse.value = it
            }
        }

//        Log.d("Nested API city ViewModel", cityResponse.toString())

        return cityResponse
    }

    fun getWeatherData(weatherRequest: WeatherRequest): LiveData<WeatherResponse> {
        val weatherResponse = MutableLiveData<WeatherResponse>()

        viewModelScope.launch {
            apiRepository.getWeatherData(weatherRequest).collect{
                weatherResponse.value = it
            }
        }

        Log.d("Nested API weather ViewModel", weatherResponse.toString())
        return weatherResponse
    }
}
package com.example.networkingandasynchronousprogramming.nestedhttpconnection.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkingandasynchronousprogramming.nestedhttpconnection.model.NetworkHelper
import kotlinx.coroutines.launch

class NestedAPIViewModel : ViewModel(){
    private val _getResponse = MutableLiveData<String>()
    private val getResponse: LiveData<String> = _getResponse


    private val _getWeatherResponse = MutableLiveData<String>()
    private val getWeatherResponse: LiveData<String> = _getWeatherResponse

    fun fetchData(city: String): LiveData<String> {
        viewModelScope.launch{
            val response = NetworkHelper.fetchData(city)
            Log.d("Nested API View Model", response)

            _getResponse.postValue(response)
        }
        return getResponse
    }

    fun fetchWeatherData(lat: String, long: String): LiveData<String>{

        viewModelScope.launch {
            val response = NetworkHelper.fetchWeatherData(lat, long)
            Log.d("Nested API Weather View Model", response)
            _getWeatherResponse.postValue(response)
        }
        return getWeatherResponse
    }
}
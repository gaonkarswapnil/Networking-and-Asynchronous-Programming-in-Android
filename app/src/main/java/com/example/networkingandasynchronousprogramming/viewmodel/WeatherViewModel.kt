package com.example.networkingandasynchronousprogramming.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkingandasynchronousprogramming.model.NetworkHelper
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {
    private val _getResponse = MutableLiveData<String>()
    val getResponse: LiveData<String> = _getResponse

    fun fetchData(): LiveData<String> {
        viewModelScope.launch{
            val response = NetworkHelper.fetchData()

            _getResponse.postValue(response)
        }
        return getResponse
    }

}
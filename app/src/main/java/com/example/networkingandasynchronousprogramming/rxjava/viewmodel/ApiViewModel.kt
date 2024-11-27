package com.example.networkingandasynchronousprogramming.rxjava.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.networkingandasynchronousprogramming.rxjava.model.APIRepository
import com.example.networkingandasynchronousprogramming.rxjava.model.CityResponse
import com.example.networkingandasynchronousprogramming.rxjava.model.WeatherRequest
import com.example.networkingandasynchronousprogramming.rxjava.model.WeatherResponse
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo

class ApiViewModel(
    val application: Application,
    val apiRepository: APIRepository
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getCityData(city: String): LiveData<CityResponse>{
        val cityResponse = MutableLiveData<CityResponse>()

        apiRepository.getCityData(city)
            .subscribe({city ->
                cityResponse.value = city
            }, {error ->

            }).addTo(compositeDisposable)


//        Log.d("Nested API city ViewModel", cityResponse.toString())

        return cityResponse
    }

    fun getWeatherData(weatherRequest: WeatherRequest): LiveData<WeatherResponse> {
        val weatherResponse = MutableLiveData<WeatherResponse>()

        apiRepository.getWeatherData(weatherRequest)
            .subscribe({weather ->
                weatherResponse.value = weather
            }, {error ->

            }).addTo(compositeDisposable)
        return weatherResponse
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
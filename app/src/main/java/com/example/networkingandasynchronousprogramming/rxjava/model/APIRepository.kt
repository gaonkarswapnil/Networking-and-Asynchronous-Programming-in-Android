package com.example.networkingandasynchronousprogramming.rxjava.model

import android.util.Log
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.flow


class APIRepository {

    val apiWeatherServices = RetrofitHelper.getWeatherApiService()
    val apiCityServices = RetrofitHelper.getCityApiService()

    fun getWeatherData(weatherRequest: WeatherRequest): Observable<WeatherResponse> {
        return apiWeatherServices.getWeatherData(
            weatherRequest.key,
            weatherRequest.location,
            weatherRequest.days,
            weatherRequest.aqi,
            weatherRequest.alerts
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    }


    fun getCityData(city: String): Observable<CityResponse> {
        return apiCityServices.getCityData(
            city
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

}
package com.example.networkingandasynchronousprogramming.livedata.model

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.flow.flow


class APIRepository {

    val apiWeatherServices = RetrofitHelper.getWeatherApiService()
    val apiCityServices = RetrofitHelper.getCityApiService()

    fun getWeatherData(weatherRequest: WeatherRequest) = flow {
        try {
            val weatherResponse = apiWeatherServices.getWeatherData(
                weatherRequest.key,
                weatherRequest.location,
                weatherRequest.days,
                weatherRequest.aqi,
                weatherRequest.alerts
            )
            if(weatherResponse!=null){
                emit(weatherResponse.body())
            }else{
                Log.d("Nested API Weather_Repository", "weatherResponse")
            }
        }catch (e: Exception){
            Log.d("weather Nested API Respository", e.message.toString())
            e.printStackTrace()
        }


    }


    fun getCityData(city: String) = flow {
        try{
            val cityResponse = apiCityServices.getCityData(
                city
            )

            if(cityResponse!=null){

//                val gson = Gson()
//                val myModel: CityResponse = gson.fromJson(cityResponse.toString(), CityResponse::class.java)
                emit(cityResponse.body())
            }else{
                Log.d("Nested API city Weather_Repository", cityResponse)
            }
        }catch (e: Exception){
            Log.d("city Nested API Respository", e.message.toString())
            e.printStackTrace()
        }


    }

}
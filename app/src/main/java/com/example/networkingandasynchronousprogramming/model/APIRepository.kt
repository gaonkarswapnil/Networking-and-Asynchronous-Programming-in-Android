package com.example.networkingandasynchronousprogramming.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class APIRepository {
    val apiWeatherServices = RetrofitHelper.getWeatherApiService()
    val apiCityServices = RetrofitHelper.getCityApiService()

    fun getCityData(city: String): LiveData<CityResponse>{
        val cityResonse = MutableLiveData<CityResponse>()

        val call = apiCityServices.getCityData(city)

        call.enqueue(object : Callback<CityResponse> {
            override fun onResponse(call: Call<CityResponse>, response: Response<CityResponse>) {
                if(response.isSuccessful){
                    cityResonse.postValue(response.body())
                }else{
                    Log.d("API Repository City", response.message())
                }
            }

            override fun onFailure(call: Call<CityResponse>, e: Throwable) {
                e.printStackTrace()
            }

        })

        return cityResonse
    }

    fun getWeatherData(weatherRequest: WeatherRequest): LiveData<WeatherResponse>{
        val weatherResponse = MutableLiveData<WeatherResponse>()
        
        val call = apiWeatherServices.getWeatherData(
            key = weatherRequest.key,
            location = weatherRequest.location,
            days = weatherRequest.days,
            aqi = weatherRequest.aqi,
            alerts = weatherRequest.alerts
        )
        
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                Log.d("API Repository Weather response", response.isSuccessful.toString())
                if(response.isSuccessful){
                    weatherResponse.postValue(response.body())
                }else{
                    Log.d("API Repository Weather", response.message())
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, e: Throwable) {
                Log.d("API Repository Weather", "error  ${e.message}")
                e.printStackTrace()
            }
        })

        return weatherResponse
    }

    private fun handleFailure(t: Throwable) {
        when (t) {
            is IOException -> {
                // Network issues (No Internet, Timeout)
                Log.e("API Repository", "Network error: ${t.message}")
            }
            is IllegalStateException -> {
                // This is unlikely, but possible for unexpected state
                Log.e("API Repository", "Unexpected state error: ${t.message}")
            }
            else -> {
                // Catch all other errors (JSON parsing issues, etc.)
                Log.e("API Repository", "Unknown error: ${t.message}")
            }
        }
        t.printStackTrace()  // Log stack trace for debugging
    }
}
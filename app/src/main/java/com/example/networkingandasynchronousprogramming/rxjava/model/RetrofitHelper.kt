package com.example.networkingandasynchronousprogramming.rxjava.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val CITY_URL = "https://api.api-ninjas.com/"
    val WEATHER_URL = "http://api.weatherapi.com/"


    // Retrofit instance for the City API
    private val retrofitCity by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor{ chain ->
                // Add the X-Api-Key header to every request
                val request = chain.request().newBuilder()
                    .addHeader("X-Api-Key", "4aFJnZw4xiuEL8e25Qsrew==hUbqFizYLsLj6bqq")
                    .build()
                chain.proceed(request)
            }
            .build()

        Retrofit.Builder()
            .baseUrl(CITY_URL)  // Use CITY_URL for City-related API
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    // Retrofit instance for the Weather API
    private val retrofitWeather by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(WEATHER_URL)  // Use WEATHER_URL for Weather-related API
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    // Get instance of ApiServices for City-related requests
    fun getCityApiService(): ApiServices {
        return retrofitCity.create(ApiServices::class.java)
    }

    // Get instance of ApiServices for Weather-related requests
    fun getWeatherApiService(): ApiServices {
        return retrofitWeather.create(ApiServices::class.java)
    }
}
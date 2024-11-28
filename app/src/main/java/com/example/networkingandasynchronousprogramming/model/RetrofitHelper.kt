package com.example.networkingandasynchronousprogramming.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    val CITY_URL = "https://api.api-ninjas.com/"
    val WEATHER_URL = "https://api.weatherapi.com/"


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
            .build()
    }

    private val retrofitWeather by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS) // Connect timeout
            .readTimeout(30, TimeUnit.SECONDS)    // Read timeout
            .writeTimeout(30, TimeUnit.SECONDS)   // Write timeout
            .build()

        Retrofit.Builder()
            .baseUrl(WEATHER_URL)  // Use WEATHER_URL for Weather-related API
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getCityApiService(): ApiServices {
        return retrofitCity.create(ApiServices::class.java)
    }

    fun getWeatherApiService(): ApiServices {
        return retrofitWeather.create(ApiServices::class.java)
    }
}
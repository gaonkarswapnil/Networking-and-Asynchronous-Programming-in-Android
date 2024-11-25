package com.example.networkingandasynchronousprogramming.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import java.net.HttpURLConnection
import java.net.URL

object NetworkHelper {
    val BASE_URL = "http://api.weatherapi.com/v1/forecast.json?key=55ae7587d2194b30bf364918242111&q=Kalyan&days=1&aqi=no&alerts=no"

    suspend fun fetchData(): String {
//        return withContext(Dispatchers.IO){
//            try {
//
//            }
//        }
        var response = ""

        val thread = Thread {
            var connection: HttpURLConnection? = null
            val url = URL(BASE_URL)
            try {
                connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"
                connection.connectTimeout = 10000
                connection.readTimeout = 15000
                connection.doInput = true

                val responseCode = connection.responseCode

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val inputStream = connection.inputStream
                    response = inputStream.bufferedReader().use { it.readText() }

                    println("Response: $response")
                } else {
                    println("Error: Response Code $responseCode")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }

        thread.start()
        thread.join()

        return response

    }
}
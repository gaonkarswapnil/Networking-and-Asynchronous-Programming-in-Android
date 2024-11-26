package com.example.networkingandasynchronousprogramming.nestedhttpconnection.model

import android.util.Log
import java.net.HttpURLConnection
import java.net.URL

object NetworkHelper {
//    private val BASE_URL_1 =
//    private val BASE_URL_2 = "http://api.weatherapi.com/v1/"

    fun fetchData(city: String): String{
        val selectedURL = "https://api.api-ninjas.com/v1/city?name=${city}"
        var response = ""

        val thread = Thread{
            var connection: HttpURLConnection? = null
            val url = URL(selectedURL)

            try {
                connection = url.openConnection() as HttpURLConnection
                connection.setRequestProperty("X-Api-Key", "4aFJnZw4xiuEL8e25Qsrew==hUbqFizYLsLj6bqq")
                connection.requestMethod = "GET"
                connection.connectTimeout = 10000
                connection.readTimeout = 15000
                connection.doInput = true

                val responseCode = connection.responseCode

                if(responseCode == HttpURLConnection.HTTP_OK){
                    val inputStream = connection.inputStream
                    response = inputStream.bufferedReader().use { it.readText() }

                    println("Response: $response")

                    Log.e("Response Network", response)

                }else{
                    println("Error: Response Code $responseCode")
                }

            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                connection?.disconnect()
            }
        }

        thread.start()
        thread.join()

        return response
    }

    suspend fun fetchWeatherData(lat: String, long: String): String{
        val selectedURL = "http://api.weatherapi.com/v1/forecast.json?key=55ae7587d2194b30bf364918242111&q=${lat},${long}&days=1&aqi=no&alerts=no"
        var response = ""

        val thread = Thread{
            var connection: HttpURLConnection? = null
            val url = URL(selectedURL)

            try {
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 10000
                connection.readTimeout = 15000
                connection.doInput = true

                val responseCode = connection.responseCode

                if(responseCode == HttpURLConnection.HTTP_OK){
                    val inputStream = connection.inputStream
                    response = inputStream.bufferedReader().use { it.readText() }

                    Log.d("Nested API Network", response)
                }else{
                    println("Error: Response Code $responseCode")
                }

            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                connection?.disconnect()
            }
        }

        thread.start()
        thread.join()

        return response
    }
}
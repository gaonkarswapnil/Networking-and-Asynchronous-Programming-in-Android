package com.example.networkingandasynchronousprogramming.httpurl

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun makeApiCallUsingThread(apiUrl: String){
    Thread{
        var response = ""
        var connection: HttpURLConnection? = null
        val url = URL(apiUrl)
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

                println("Response: $response")
            }else{
                println("Error: Response Code $responseCode")
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        finally {
            connection?.disconnect()
        }
    }.start()
}

fun main(){
    makeApiCallUsingThread("http://api.weatherapi.com/v1/forecast.json?key=55ae7587d2194b30bf364918242111&q=Kalyan&days=1&aqi=no&alerts=no")
}
package com.example.networkingandasynchronousprogramming.nestedhttpconnection.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.networkingandasynchronousprogramming.R
import com.example.networkingandasynchronousprogramming.databinding.ActivityNestedApiactivityBinding
import com.example.networkingandasynchronousprogramming.model.WeatherResponse
import com.example.networkingandasynchronousprogramming.nestedhttpconnection.model.CityResponse
import com.example.networkingandasynchronousprogramming.nestedhttpconnection.model.CityResponseItem
import com.example.networkingandasynchronousprogramming.nestedhttpconnection.viewmodel.NestedAPIViewModel
import com.example.networkingandasynchronousprogramming.viewmodel.WeatherViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject

class NestedAPIActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNestedApiactivityBinding

    lateinit var location: String

    private lateinit var lat: String
    private lateinit var long: String

    private val weatherViewModel: NestedAPIViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNestedApiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        binding.btnSearch.setOnClickListener {
            location = binding.etSearchLocation.text.toString()

            weatherViewModel.fetchData(location).observe(this, Observer { response->
                if(response!=null){
                    Log.d("Nested API", response)
                    val gson = Gson()
                    val jsonObject = gson.fromJson(response, CityResponse::class.java)

                    if(jsonObject.isNotEmpty()){
                        binding.etLat.setText(jsonObject[0].latitude.toString())
                        lat = jsonObject[0].latitude.toString()
                        binding.etLong.setText(jsonObject[0].longitude.toString())
                        long = jsonObject[0].longitude.toString()
                    }

                    Log.d("Nested API Lat and Long", "$lat , $long")

                    weatherViewModel.fetchWeatherData(lat, long).observe(this, Observer { weatherResponse->
                        if (weatherResponse!=null){
                            Log.d("Nested API", weatherResponse)
                            val gSon = Gson()
                            val weatherJsonObject = gSon.fromJson(weatherResponse, WeatherResponse::class.java)

                            Log.d("Nested API Object", weatherJsonObject.toString())
                            binding.tvTemp.text = weatherJsonObject.current.temp_c.toString()
                        }
                    })

                }
            })



        }


    }
}
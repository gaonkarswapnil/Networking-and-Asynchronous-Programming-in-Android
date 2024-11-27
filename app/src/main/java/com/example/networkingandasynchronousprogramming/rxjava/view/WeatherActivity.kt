package com.example.networkingandasynchronousprogramming.rxjava.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.networkingandasynchronousprogramming.R
import com.example.networkingandasynchronousprogramming.databinding.ActivityWeatherBinding
import com.example.networkingandasynchronousprogramming.rxjava.model.APIRepository
import com.example.networkingandasynchronousprogramming.rxjava.model.WeatherRequest
import com.example.networkingandasynchronousprogramming.rxjava.viewmodel.ApiViewModel
import com.example.networkingandasynchronousprogramming.rxjava.viewmodel.ApiViewModelFactory

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding

    lateinit var location: String

    private lateinit var lat: String
    private lateinit var long: String


    private val weatherViewModel: ApiViewModel by viewModels {
        ApiViewModelFactory(application, APIRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {

            location = binding.etSearchLocation.text.toString()

            weatherViewModel.getCityData(location).observe(this, Observer { response->
                if(response!=null){
                    Log.d("Nested API", response.toString())
//                    val gson = Gson()
//                    val jsonObject = gson.fromJson(response, CityResponse::class.java)

//                    if(jsonObject.isNotEmpty()){
                    binding.etLat.setText(response[0].latitude.toString())
                    lat = response[0].latitude.toString()
                    binding.etLong.setText(response[0].longitude.toString())
                    long = response[0].longitude.toString()
//                    }

                    Log.d("Nested API Lat and Long", "$lat , $long")


                    val city = "$lat,$long"
                    val request = WeatherRequest (
                        "55ae7587d2194b30bf364918242111",
                        city,
                        1,
                        "no",
                        "no"
                    )
                    weatherViewModel.getWeatherData(request).observe(this, Observer { weatherResponse->
                        if (weatherResponse!=null){
                            Log.d("Nested API", weatherResponse.toString())
//                            val gSon = Gson()
//                            val weatherJsonObject = gSon.fromJson(weatherResponse, WeatherResponse::class.java)

//                            Log.d("Nested API Object", weatherJsonObject.toString())
                            binding.tvTemp.text = weatherResponse.current.temp_c.toString()
                        }
                    })

                }
            })

        }
    }
}
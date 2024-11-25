package com.example.networkingandasynchronousprogramming.controller

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.networkingandasynchronousprogramming.databinding.ActivityMainBinding
import com.example.networkingandasynchronousprogramming.model.WeatherResponse
import com.example.networkingandasynchronousprogramming.viewmodel.WeatherViewModel
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherViewModel.fetchData()
        weatherViewModel.getResponse.observe(this, Observer { response ->
            if(response!= null){
                val gson = Gson()

                val JsonObject = gson.fromJson(response, WeatherResponse::class.java)

                binding.tvCityName.text = JsonObject.location.name.toString()
                binding.tvTemperature.text = JsonObject.current.temp_c.toString()
                binding.tvCondition.text = JsonObject.current.condition.text.toString()

                Glide.with(this)
                    .load("https:${JsonObject.current.condition.icon.toString()}")
                    .into(binding.imgWeatherIcon)
                Log.d("Advance Networking and Async", response.toString())
            }
        })



    }
}
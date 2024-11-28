package com.example.networkingandasynchronousprogramming.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.networkingandasynchronousprogramming.R
import com.example.networkingandasynchronousprogramming.databinding.ActivityNestedWeatherBinding
import com.example.networkingandasynchronousprogramming.model.APIRepository
import com.example.networkingandasynchronousprogramming.model.WeatherRequest
import com.example.networkingandasynchronousprogramming.viewmodel.ApiViewModel
import com.example.networkingandasynchronousprogramming.viewmodel.ApiViewModelFactory


class NestedWeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNestedWeatherBinding

    lateinit var location: String

    private lateinit var lat: String
    private lateinit var long: String


    private val weatherViewModel: ApiViewModel by viewModels {
        ApiViewModelFactory(application, APIRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNestedWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }


        binding.btnSearch.setOnClickListener {
            location = binding.etSearchLocation.text.toString()

            // Observe the city data response
            weatherViewModel.getCityData(location).observe(this, Observer { response ->
                if (response != null && response.isNotEmpty()) {
                    val city = response[0]
                    lat = city.latitude.toString()
                    long = city.longitude.toString()

                    binding.etLat.setText(lat)
                    binding.etLong.setText(long)

                    // Create a weather request based on the city coordinates
                    val request = WeatherRequest(
                        key = "55ae7587d2194b30bf364918242111",
                        location = "$lat,$long",
                        days = 1,
                        aqi = "no",
                        alerts = "no"
                    )

                    // Observe the weather data response
                    weatherViewModel.getWeatherData(request).observe(this, Observer { weatherResponse ->
                        if (weatherResponse != null) {
                            binding.tvTemp.text = weatherResponse.current.temp_c.toString()
                        }
                    })

                } else {
                    // Show a message if the city was not found
                    Toast.makeText(this, "City not found or invalid!", Toast.LENGTH_SHORT).show()
                }
            })


        }
    }
}
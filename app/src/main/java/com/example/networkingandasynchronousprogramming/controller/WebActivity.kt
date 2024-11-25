package com.example.networkingandasynchronousprogramming.controller

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.networkingandasynchronousprogramming.R
import com.example.networkingandasynchronousprogramming.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val cityName = intent.getStringExtra("CITY_NAME") ?: "Kalyan"
        val searchUrl = "https://www.google.com/search?q=$cityName"

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient() // Ensures links open within WebView
        binding.webView.loadUrl(searchUrl)

    }
}
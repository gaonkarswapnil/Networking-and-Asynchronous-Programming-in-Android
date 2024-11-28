package com.example.networkingandasynchronousprogramming.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.networkingandasynchronousprogramming.model.APIRepository

class ApiViewModelFactory(
    private val application: Application,
    private val apiResponse: APIRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return super.create(modelClass)
        if(modelClass.isAssignableFrom(ApiViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ApiViewModel(application, apiResponse) as T
        }
        throw IllegalArgumentException("Unknown Argument")
    }
}
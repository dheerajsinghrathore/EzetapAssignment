package com.android.dynamiclayout.viewmodel

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.dynamiclayout.model.CustomResponse
import com.android.dynamiclayout.util.NetworkUtils
import com.android.dynamiclayout.api.ApiService

class MainRepository(
    private val apiService: ApiService,
    private val context: Context,
) {

    private val customLiveData = MutableLiveData<CustomResponse>()

    val customResponse: LiveData<CustomResponse>
        get() = customLiveData

    suspend fun getCustomUIApi() {
        if (NetworkUtils.isInternetAvailable(context)) {
            val result = apiService.getUsers()
            Log.e("TAG - ", "getUserApi: $result")
            if (result.body() != null) {
                customLiveData.postValue(result.body())
            } else {
                Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, "Please check internet connection!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}
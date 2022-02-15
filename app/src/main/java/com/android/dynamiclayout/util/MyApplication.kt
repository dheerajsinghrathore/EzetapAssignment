package com.android.dynamiclayout.util

import android.app.Application
import com.android.dynamiclayout.api.ApiService
import com.android.dynamiclayout.api.RetrofitHandler
import com.android.dynamiclayout.viewmodel.MainRepository

class MyApplication : Application() {

    lateinit var mainRepository: MainRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val userService = RetrofitHandler.getInstance().create(ApiService::class.java)
        mainRepository = MainRepository(userService, applicationContext)
    }
}
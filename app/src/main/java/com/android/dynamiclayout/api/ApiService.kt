package com.android.dynamiclayout.api

import com.android.dynamiclayout.model.CustomResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("android_assignment.json")
    suspend fun getUsers(): Response<CustomResponse>
}
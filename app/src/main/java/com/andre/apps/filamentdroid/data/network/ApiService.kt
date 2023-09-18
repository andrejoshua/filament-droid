package com.andre.apps.filamentdroid.data.network

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v2/users")
    suspend fun getUser(): Response<UserResponse>
}

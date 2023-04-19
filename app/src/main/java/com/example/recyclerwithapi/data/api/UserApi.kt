package com.example.recyclerwithapi.data.api

import com.example.recyclerwithapi.data.model.request.LoginRequest
import com.example.recyclerwithapi.data.model.response.LoginResponse
import com.example.recyclerwithapi.di.ApiClient
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/api/authaccount/login")
    suspend fun loginUser(@Body loginReq: LoginRequest):Response<LoginResponse>

    companion object{
        fun getApi():UserApi?{
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}
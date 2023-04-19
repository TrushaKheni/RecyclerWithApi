package com.example.recyclerwithapi.data.repository

import com.example.recyclerwithapi.data.api.UserApi
import com.example.recyclerwithapi.data.model.request.LoginRequest
import com.example.recyclerwithapi.data.model.response.LoginResponse
import retrofit2.Response

class UserRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return  UserApi.getApi()?.loginUser(loginReq = loginRequest)
    }
}
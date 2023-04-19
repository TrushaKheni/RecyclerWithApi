package com.example.recyclerwithapi.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recyclerwithapi.data.model.request.LoginRequest
import com.example.recyclerwithapi.data.model.response.BaseResponse
import com.example.recyclerwithapi.data.model.response.LoginResponse
import com.example.recyclerwithapi.data.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    val userRepo = UserRepository()
    val loginResult : MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()

    fun loginUser(email:String,password:String) {
        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val loginRequest= LoginRequest(email, password)
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.code() == 200) {
                    loginResult.value = BaseResponse.Success(response.body())
                }
                else {
                    loginResult.value = BaseResponse.Error(response?.message())
                }
            }catch (e: Exception) {
                loginResult.value = BaseResponse.Error(e.message)
            }
        }

    }

}
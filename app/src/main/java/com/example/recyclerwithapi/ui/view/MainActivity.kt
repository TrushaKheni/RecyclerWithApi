package com.example.recyclerwithapi.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.recyclerwithapi.data.model.response.BaseResponse
import com.example.recyclerwithapi.databinding.ActivityMainBinding
import com.example.recyclerwithapi.di.SessionManager
import com.example.recyclerwithapi.ui.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {
    lateinit var moBinding: ActivityMainBinding
    val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(moBinding.root)

        val token = SessionManager.getToken(this)
        if (token.isNullOrBlank()){
            navigateToHome()
        }

        viewModel.loginResult.observe(this) {
            when(it){
                is BaseResponse.Loading -> {
                    showLoading()
                }
                is BaseResponse.Success -> {
                    stopLoading()
                    processLogin(it.data)
                }
                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }

        moBinding.btnLogin.setOnClickListener {
            doLogin()
        }

    }
}
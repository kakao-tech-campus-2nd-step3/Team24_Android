package com.example.challengeonairandroid.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.challengeonairandroid.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // viewModel 사용
    }
}
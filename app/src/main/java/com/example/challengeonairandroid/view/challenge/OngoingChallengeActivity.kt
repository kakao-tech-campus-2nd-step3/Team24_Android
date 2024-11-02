package com.example.challengeonairandroid.view.challenge

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.challengeonairandroid.viewmodel.ChallengeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OngoingChallengeActivity : AppCompatActivity() {
    private val ongoingChallengeDetailViewModel: ChallengeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // viewModel 사용
    }
}
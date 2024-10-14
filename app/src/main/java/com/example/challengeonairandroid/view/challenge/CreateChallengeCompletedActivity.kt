package com.example.challengeonairandroid.view.challenge

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.challengeonairandroid.R
import com.example.challengeonairandroid.viewmodel.ChallengeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateChallengeCompletedActivity : AppCompatActivity() {
    private val createChallengeViewModel: ChallengeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_challenge_completed)
    }
}
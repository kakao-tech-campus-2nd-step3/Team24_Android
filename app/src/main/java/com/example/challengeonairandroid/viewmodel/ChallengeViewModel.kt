package com.example.challengeonairandroid.viewmodel

import androidx.lifecycle.ViewModel
import com.example.challengeonairandroid.model.repository.ChallengeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChallengeViewModel @Inject constructor(private val challengeRepository: ChallengeRepository) : ViewModel()
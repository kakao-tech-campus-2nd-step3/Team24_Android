package com.example.challengeonairandroid.viewmodel

import androidx.lifecycle.ViewModel
import com.example.challengeonairandroid.model.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel()
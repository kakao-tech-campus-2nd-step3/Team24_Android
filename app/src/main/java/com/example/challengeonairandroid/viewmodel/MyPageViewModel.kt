package com.example.challengeonairandroid.viewmodel

import androidx.lifecycle.ViewModel
import com.example.challengeonairandroid.model.repository.MyPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(private val myPageRepository: MyPageRepository) : ViewModel() {

}

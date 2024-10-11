package com.example.challengeonairandroid.view.mypage

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeonairandroid.R
import com.example.challengeonairandroid.databinding.ActivityMyPageHistoryBinding
import com.example.challengeonairandroid.model.data.History
import com.example.challengeonairandroid.viewmodel.MyPageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageHistoryActivity : AppCompatActivity() {
    private val myPageViewModel: MyPageViewModel by viewModels()
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myPageHistoryBinding: ActivityMyPageHistoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_page_history)
        myPageHistoryBinding.myPageHistoryData = myPageViewModel
        myPageHistoryBinding.lifecycleOwner = this

        val rvHistory = myPageHistoryBinding.rvHistory

        val dummyHistoryList = listOf(
            History("하루 30분 모각독", "11:00", "11:30", "08.24", isSucceed = true, isHost = true),
            History("방 처음 파본다", "10:30", "11:00", "08.25", isSucceed = true, isHost = true)
        )

        val btnBack = myPageHistoryBinding.btnBack
        btnBack.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
            finish()
        }

        historyAdapter = HistoryAdapter(dummyHistoryList)
        rvHistory.layoutManager = LinearLayoutManager(this)
        rvHistory.adapter = historyAdapter
    }
}
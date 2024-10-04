package com.example.challengeonairandroid.view.mypage

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeonairandroid.R
import com.example.challengeonairandroid.model.data.Challenge
import com.example.challengeonairandroid.model.data.History
import com.example.challengeonairandroid.view.mypage.HistoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_history)

        val rvHistory = findViewById<RecyclerView>(R.id.rvHistory)

        val dummyHistoryList = listOf(
            History("1", "1", "1", isSucceed = true, isHost = true),
            History("2", "2", "2", isSucceed = true, isHost = true)
        )

        val dummyChallengeList = listOf(
            Challenge(
                imageUrl = "https://images.pexels.com/photos/20883985/pexels-photo-20883985.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                challengeName = "독서 챌린지 방 처음 파본다...",
                hostId = 1
            ),
            Challenge(
                imageUrl = "https://images.pexels.com/photos/20883985/pexels-photo-20883985.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                challengeName = "독서 챌린지 방 처음 파본다...",
                hostId = 1
            )
        )

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
            finish()
        }

        val historyAdapter = HistoryAdapter(dummyHistoryList)
        rvHistory.layoutManager = LinearLayoutManager(this)
        rvHistory.adapter = historyAdapter
    }
}
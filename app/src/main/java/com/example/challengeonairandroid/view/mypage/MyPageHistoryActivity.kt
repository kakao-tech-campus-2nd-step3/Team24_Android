package com.example.challengeonairandroid.view.mypage.history

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeonairandroid.R
import com.example.challengeonairandroid.model.data.Challenge
import com.example.challengeonairandroid.model.data.History
import com.example.challengeonairandroid.view.mypage.MyPageActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_history)

        val rvHistory = findViewById<RecyclerView>(R.id.rvHistory)

        val dummyHistoryList = listOf(
            History(1, 1, 1, true, true),
            History(2, 2, 2, false, false)
        )

        val dummyChallengeList = listOf(
            Challenge(
                1, 1, 1,
                "https://images.pexels.com/photos/20883985/pexels-photo-20883985.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "독서 챌린지", "방 처음 파본다...", 100,
                "2024-10-01", "09:00", "11:00", 10, 2
            ),
            Challenge(
                2, 2, 2,
                "https://images.pexels.com/photos/2827392/pexels-photo-2827392.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "운동 챌린지", "매일30분 운동하기", 200,
                "2024-09-30", "08:00", "10:00", 20, 5
            )
        )

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
            finish()
        }

        val historyAdapter = HistoryAdapter(dummyHistoryList, dummyChallengeList)
        rvHistory.layoutManager = LinearLayoutManager(this)
        rvHistory.adapter = historyAdapter
    }
}
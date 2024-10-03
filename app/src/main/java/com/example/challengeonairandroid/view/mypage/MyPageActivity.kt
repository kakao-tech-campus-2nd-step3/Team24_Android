package com.example.challengeonairandroid.view.mypage

import android.content.Intent
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeonairandroid.R
import com.example.challengeonairandroid.view.home.HomeActivity
import com.example.challengeonairandroid.view.search.SearchActivity
import com.example.challengeonairandroid.viewmodel.MyPageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageActivity : AppCompatActivity() {
    private val myPageViewModel: MyPageViewModel by viewModels()
    private lateinit var waitingChallengeAdapter: WaitingChallengeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        val waitingChallengeRecyclerView = findViewById<RecyclerView>(R.id.rvWaitingChallengeList)
        waitingChallengeRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val waitingChallengeAdapter = WaitingChallengeAdapter(myPageViewModel.challenges.value)
        waitingChallengeRecyclerView.adapter = waitingChallengeAdapter

        val challengeHistory = findViewById<GridLayout>(R.id.glChallengeList)
        val historyIntent = Intent(this, MyPageHistoryActivity::class.java)
        challengeHistory.setOnClickListener {
            startActivity(historyIntent)
        }

        val challengeSearch = findViewById<ImageButton>(R.id.ibSearch)
        val searchIntent = Intent(this, SearchActivity::class.java)
        challengeSearch.setOnClickListener {
            startActivity(searchIntent)
        }

        val challengeHome = findViewById<ImageButton>(R.id.ibHome)
        val homeIntent = Intent(this, HomeActivity::class.java)
        challengeHome.setOnClickListener {
            startActivity(homeIntent)
        }

    }
}

package com.example.challengeonairandroid.view.mypage

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeonairandroid.R
import com.example.challengeonairandroid.databinding.ActivityMyPageBinding
import com.example.challengeonairandroid.view.home.HomeActivity
import com.example.challengeonairandroid.view.search.SearchActivity
import com.example.challengeonairandroid.viewmodel.MyPageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageActivity : AppCompatActivity() {
    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myPageViewModel.initData()

        val myPageBinding: ActivityMyPageBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_page)
        myPageBinding.myPageData = myPageViewModel
        myPageBinding.lifecycleOwner = this

        val waitingChallengeRecyclerView = myPageBinding.rvWaitingChallengeList
        waitingChallengeRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val waitingChallengeAdapter = WaitingChallengeAdapter(myPageViewModel.waitingChallenges, myPageViewModel.userId)
        waitingChallengeRecyclerView.adapter = waitingChallengeAdapter

        myPageViewModel.waitingChallenges.observe(this, { challenges ->
            waitingChallengeAdapter.notifyDataSetChanged()
        })

        myPageViewModel.user.observe(this, { user ->
            waitingChallengeAdapter.notifyDataSetChanged()
        })

        val challengeHistory = myPageBinding.glChallengeList
        val historyIntent = Intent(this, MyPageHistoryActivity::class.java)
        challengeHistory.setOnClickListener {
            startActivity(historyIntent)
        }

        val home = myPageBinding.ibHome
        val homeIntent = Intent(this, HomeActivity::class.java)
        home.setOnClickListener {
            startActivity(homeIntent)
        }

        val search = myPageBinding.ibSearch
        val searchIntent = Intent(this, SearchActivity::class.java)
        search.setOnClickListener {
            startActivity(searchIntent)
        }

    }
}
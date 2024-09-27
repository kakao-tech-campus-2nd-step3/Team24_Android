package com.example.challengeonairandroid.view.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengeonairandroid.R
import com.example.challengeonairandroid.model.Challenge
import com.example.challengeonairandroid.model.User

class WaitingChallengeAdapter(
    private val waitingChallengeList: List<Challenge>,
    private val user: User
) : RecyclerView.Adapter<WaitingChallengeAdapter.WaitingChallengeViewHolder>() {

    // ViewHolder 클래스
    class WaitingChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val challengeTitle: TextView = itemView.findViewById(R.id.tvChallengeName)
        val challengeStatus: TextView = itemView.findViewById(R.id.tvMadeByMe)
        val challengeImage: ImageView = itemView.findViewById(R.id.ivChallengeImg)
    }

    // 아이템 레이아웃을 ViewHolder로 변환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaitingChallengeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.waiting_challenge_item, parent, false)
        return WaitingChallengeViewHolder(view)
    }

    // ViewHolder에 데이터를 바인딩
    override fun onBindViewHolder(holder: WaitingChallengeViewHolder, position: Int) {
        val currentChallenge = waitingChallengeList[position]
        holder.challengeTitle.text = currentChallenge.challengeName
        if (currentChallenge.hostId == user.userId) {
            holder.challengeStatus.visibility = View.VISIBLE
        }
        else {
            holder.challengeStatus.visibility = View.GONE
        }
        Glide.with(holder.itemView.context)
            .load(currentChallenge.imageUrl)
            .placeholder(R.drawable.sample_challenge_thumbnail)
            .into(holder.challengeImage)
    }

    // 아이템의 수 반환
    override fun getItemCount(): Int {
        return waitingChallengeList.size
    }
}
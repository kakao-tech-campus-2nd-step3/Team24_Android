package com.example.challengeonairandroid.view.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeonairandroid.R
import com.example.challengeonairandroid.model.data.Challenge
import com.example.challengeonairandroid.model.data.History

class HistoryAdapter(
    private val historyList: List<History>,
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = historyList[position]

        // 날짜 설정
        holder.tvDate.text = "08.24"
        // 챌린지 이미지 설정 (Glide/Picasso 등을 사용할 수 있습니다)
        holder.ivChallengeCover.setImageResource(R.drawable.sample_history_cover) // 실제 이미지 로드 필요

        // 성공/실패 여부
        if (history.isSucceed) {
            holder.tvSuccess.visibility = View.VISIBLE
            holder.tvFail.visibility = View.GONE
        } else {
            holder.tvSuccess.visibility = View.GONE
            holder.tvFail.visibility = View.VISIBLE
        }

        // 방장 여부
        holder.tvMade.visibility = if (history.isHost) View.VISIBLE else View.GONE

        // 챌린지 제목 및 설명
        holder.tvTitle.text = history.challengeName

        // 시작 시간 및 종료 시간
        holder.tvStartTime.text = history.challengeStartTime
        holder.tvEndTime.text = history.challengeEndTime
    }

    override fun getItemCount(): Int = historyList.size

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val ivChallengeCover: ImageView = itemView.findViewById(R.id.ivChallengeCover)
        val tvSuccess: TextView = itemView.findViewById(R.id.tvSuccess)
        val tvFail: TextView = itemView.findViewById(R.id.tvFail)
        val tvMade: TextView = itemView.findViewById(R.id.tvMade)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvStartTime: TextView = itemView.findViewById(R.id.tvStartTime)
        val tvEndTime: TextView = itemView.findViewById(R.id.tvEndTime)
    }
}
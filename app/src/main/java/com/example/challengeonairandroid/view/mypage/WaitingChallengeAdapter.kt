package com.example.challengeonairandroid.view.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengeonairandroid.R
import com.example.challengeonairandroid.databinding.WaitingChallengeItemBinding
import com.example.challengeonairandroid.model.data.Challenge

class WaitingChallengeAdapter(
    private val waitingChallengeList: List<Challenge>,
) : RecyclerView.Adapter<WaitingChallengeAdapter.WaitingChallengeViewHolder>() {

    class WaitingChallengeViewHolder(val binding: WaitingChallengeItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaitingChallengeViewHolder {
        val binding: WaitingChallengeItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.waiting_challenge_item,
            parent,
            false
        )
        return WaitingChallengeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WaitingChallengeViewHolder, position: Int) {
        val currentChallenge = waitingChallengeList[position]
        holder.binding.challenge = currentChallenge

        Glide.with(holder.itemView.context)
            .load(currentChallenge.imageUrl)
            .placeholder(R.drawable.sample_challenge_thumbnail)
            .error(R.drawable.sample_challenge_thumbnail)
            .into(holder.binding.ivChallengeImg)
    }

    override fun getItemCount(): Int {
        return waitingChallengeList.size
    }
}
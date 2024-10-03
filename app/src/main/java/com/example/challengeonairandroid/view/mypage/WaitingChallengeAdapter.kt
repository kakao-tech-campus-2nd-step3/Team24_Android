package com.example.challengeonairandroid.view.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengeonairandroid.R
import com.example.challengeonairandroid.databinding.WaitingChallengeItemBinding

class WaitingChallengeAdapter(
    private var waitingChallengeList: LiveData<List<Challenge>>,
    private var userId: LiveData<Long>
) : RecyclerView.Adapter<WaitingChallengeAdapter.WaitingChallengeViewHolder>() {

    class WaitingChallengeViewHolder(val binding: WaitingChallengeItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaitingChallengeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val waitingChallengeItemBinding = WaitingChallengeItemBinding.inflate(layoutInflater, parent, false)
        return WaitingChallengeViewHolder(waitingChallengeItemBinding)
    }

    override fun onBindViewHolder(holder: WaitingChallengeViewHolder, position: Int) {
        val currentChallenge = waitingChallengeList.value?.get(position)
        val currentUserId = userId.value

        if (currentChallenge != null) {
            holder.binding.tvChallengeName.text = currentChallenge.challengeName
        }

        Glide.with(holder.itemView.context)
            .load(currentChallenge!!.challengeImgUrl)
            .placeholder(R.drawable.sample_challenge_thumbnail)
            .into(holder.binding.ivChallengeImg)

        if (currentChallenge.hostId == currentUserId) {
            holder.binding.tvMyCreatedBanner.visibility = View.VISIBLE
        }
        else {
            holder.binding.tvMyCreatedBanner.visibility = View.GONE
        }

        holder.binding.executePendingBindings()

    }

    override fun getItemCount(): Int {
        return waitingChallengeList.value?.size ?: 0
    }
}
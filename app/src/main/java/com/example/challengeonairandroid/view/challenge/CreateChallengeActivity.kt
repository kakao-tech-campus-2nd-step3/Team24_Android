package com.example.challengeonairandroid.view.challenge

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.challengeonairandroid.R
import com.example.challengeonairandroid.databinding.ActivityCreateChallengeBinding
import com.example.challengeonairandroid.viewmodel.ChallengeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateChallengeActivity : AppCompatActivity() {
    private val createChallengeDetailViewModel: ChallengeDetailViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_challenge)

        val createChallengeBinding: ActivityCreateChallengeBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_challenge)
        createChallengeBinding.lifecycleOwner = this

        createChallengeBinding.ibClose.setOnClickListener {
        }

        // 챌린지 생성 1단계 ---
        var categoryId: Int = -1

        createChallengeBinding.rgChallengeCategory.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbExercise -> categoryId = 0
                R.id.rbDevelopment -> categoryId = 1
                R.id.rbHobby -> categoryId = 2
                R.id.rbStudy -> categoryId = 3
            }
            Log.d("createChallenge", "${ categoryId }")
        }


        lateinit var challengeTitle: String
        createChallengeBinding.etChallengeTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                challengeTitle = s.toString()
                Log.d("createChallenge", "${challengeTitle}")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        lateinit var challengeBody: String
        createChallengeBinding.etChallengeBody.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                challengeBody = s.toString()
                Log.d("createChallenge", "${challengeBody}")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        createChallengeBinding.btnMinusMinNum.setOnClickListener {
            val currentValue = createChallengeBinding.tvMinNum.text.toString().toInt()
            if (currentValue > 2) {
                createChallengeBinding.tvMinNum.text = (currentValue - 1).toString()
            }
            else {
                Toast.makeText(this, "인원은 최소 2명입니다.", Toast.LENGTH_SHORT).show()
            }
        }

        createChallengeBinding.btnPlusMinNum.setOnClickListener {
            val currentValue = createChallengeBinding.tvMinNum.text.toString().toInt()
            if (currentValue < 8) {
                createChallengeBinding.tvMinNum.text = (currentValue + 1).toString()
            }
            else {
                Toast.makeText(this, "인원은 최대 8명입니다.", Toast.LENGTH_SHORT).show()
            }
        }

        createChallengeBinding.btnMinusMaxNum.setOnClickListener {
            val currentValue = createChallengeBinding.tvMaxNum.text.toString().toInt()
            if (currentValue > 2) {
                createChallengeBinding.tvMaxNum.text = (currentValue - 1).toString()
            }
            else {
                Toast.makeText(this, "인원은 최소 2명입니다.", Toast.LENGTH_SHORT).show()
            }

        }

        createChallengeBinding.btnPlusMaxNum.setOnClickListener {
            val currentValue = createChallengeBinding.tvMaxNum.text.toString().toInt()
            if (currentValue < 8) {
                createChallengeBinding.tvMaxNum.text = (currentValue + 1).toString()
            }
            else {
                Toast.makeText(this, "인원은 최대 8명입니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 갤러리에서 이미지 골라오기
        createChallengeBinding.ivChallengeImg.setOnClickListener {

        }

        createChallengeBinding.btnNext.setOnClickListener {
            createChallengeBinding.svCreateStep1.visibility = View.GONE
            createChallengeBinding.svCreateStep2.visibility = View.VISIBLE
            createChallengeBinding.btnChallengeStep2.setBackgroundResource(R.drawable.create_challenge_selected_button)
            createChallengeBinding.btnChallengeStep2.setTextColor(Color.WHITE)
        }

        createChallengeBinding.btnPrevious.setOnClickListener {
            createChallengeBinding.svCreateStep1.visibility = View.VISIBLE
            createChallengeBinding.svCreateStep2.visibility = View.GONE
            createChallengeBinding.btnChallengeStep2.setBackgroundResource(R.drawable.create_challenge_unselected_button)
            createChallengeBinding.btnChallengeStep2.setTextColor(Color.BLACK)
        }

        // 챌린지 생성 2단계 ---
        createChallengeBinding.btnStartTime.setOnClickListener {
            if (createChallengeBinding.llStartTimeLayout.visibility == View.VISIBLE) {
                createChallengeBinding.btnStartTime.setBackgroundResource(R.drawable.create_challenge_uncompleted)
                createChallengeBinding.llStartTimeLayout.visibility = View.GONE
            }
            else {
                createChallengeBinding.btnStartTime.setBackgroundResource(R.drawable.create_challenge_completed)
                createChallengeBinding.llStartTimeLayout.visibility = View.VISIBLE
            }
        }

        createChallengeBinding.btnEndTime.setOnClickListener {
            if (createChallengeBinding.llEndTimeLayout.visibility == View.VISIBLE) {
                createChallengeBinding.btnEndTime.setBackgroundResource(R.drawable.create_challenge_uncompleted)
                createChallengeBinding.llEndTimeLayout.visibility = View.GONE
            }
            else {
                createChallengeBinding.btnEndTime.setBackgroundResource(R.drawable.create_challenge_completed)
                createChallengeBinding.llEndTimeLayout.visibility = View.VISIBLE
            }
        }

        createChallengeBinding.btnSetChallengePoint.setOnClickListener {
            if (createChallengeBinding.llSetChallengePoint.visibility == View.VISIBLE) {
                createChallengeBinding.btnSetChallengePoint.setBackgroundResource(R.drawable.create_challenge_uncompleted)
                createChallengeBinding.llSetChallengePoint.visibility = View.GONE
            }
            else {
                createChallengeBinding.btnSetChallengePoint.setBackgroundResource(R.drawable.create_challenge_completed)
                createChallengeBinding.llSetChallengePoint.visibility = View.VISIBLE
            }
        }

        lateinit var challengePoint: String
        createChallengeBinding.etSetChallengePoint.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                challengePoint = s.toString()
                Log.d("createChallenge", "${challengePoint}")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

    }
}
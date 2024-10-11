package com.example.challengeonairandroid.view.challenge

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.example.challengeonairandroid.R
import com.example.challengeonairandroid.viewmodel.ChallengeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateChallengeActivity : AppCompatActivity() {
    private val createChallengeViewModel: ChallengeViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_challenge)

        // 챌린지 생성 페이지를 연 전 페이지로 돌아가기
        findViewById<ImageButton>(R.id.ibClose).setOnClickListener {

        }

        // 챌린지 생성 1단계 ---
        var categoryId: Int = -1

        findViewById<RadioGroup>(R.id.rgChallengeCategory).setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbExercise -> categoryId = 0
                R.id.rbDevelopment -> categoryId = 1
                R.id.rbHobby -> categoryId = 2
                R.id.rbStudy -> categoryId = 3
            }
            Log.d("createChallenge", "${ categoryId }")
        }


        lateinit var challengeTitle: String
        findViewById<EditText>(R.id.etChallengeTitle).addTextChangedListener(object : TextWatcher {
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
        findViewById<EditText>(R.id.etChallengeBody).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                challengeBody = s.toString()
                Log.d("createChallenge", "${challengeBody}")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        findViewById<Button>(R.id.btnMinusMinNum).setOnClickListener {
            val currentValue = findViewById<TextView>(R.id.tvMinNum).text.toString().toInt()
            if (currentValue > 2) {
                findViewById<TextView>(R.id.tvMinNum).text = (currentValue - 1).toString()
            }
            else {
                Toast.makeText(this, "인원은 최소 2명입니다.", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnPlusMinNum).setOnClickListener {
            val currentValue = findViewById<TextView>(R.id.tvMinNum).text.toString().toInt()
            if (currentValue < 8) {
                findViewById<TextView>(R.id.tvMinNum).text = (currentValue + 1).toString()
            }
            else {
                Toast.makeText(this, "인원은 최대 8명입니다.", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnMinusMaxNum).setOnClickListener {
            val currentValue = findViewById<TextView>(R.id.tvMaxNum).text.toString().toInt()
            if (currentValue > 2) {
                findViewById<TextView>(R.id.tvMaxNum).text = (currentValue - 1).toString()
            }
            else {
                Toast.makeText(this, "인원은 최소 2명입니다.", Toast.LENGTH_SHORT).show()
            }

        }

        findViewById<Button>(R.id.btnPlusMaxNum).setOnClickListener {
            val currentValue = findViewById<TextView>(R.id.tvMaxNum).text.toString().toInt()
            if (currentValue < 8) {
                findViewById<TextView>(R.id.tvMaxNum).text = (currentValue + 1).toString()
            }
            else {
                Toast.makeText(this, "인원은 최대 8명입니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 갤러리에서 이미지 골라오기
        findViewById<ImageView>(R.id.ivChallengeImg).setOnClickListener {

        }

        findViewById<Button>(R.id.btnNext).setOnClickListener {
            findViewById<NestedScrollView>(R.id.svCreateStep1).visibility = View.GONE
            findViewById<NestedScrollView>(R.id.svCreateStep2).visibility = View.VISIBLE
            findViewById<Button>(R.id.btnChallengeStep2).setBackgroundResource(R.drawable.create_challenge_selected_button)
            findViewById<Button>(R.id.btnChallengeStep2).setTextColor(Color.WHITE)
        }

        findViewById<Button>(R.id.btnPrevious).setOnClickListener {
            findViewById<NestedScrollView>(R.id.svCreateStep1).visibility = View.VISIBLE
            findViewById<NestedScrollView>(R.id.svCreateStep2).visibility = View.GONE
            findViewById<Button>(R.id.btnChallengeStep2).setBackgroundResource(R.drawable.create_challenge_unselected_button)
            findViewById<Button>(R.id.btnChallengeStep2).setTextColor(Color.BLACK)
        }

        // 챌린지 생성 2단계 ---
        findViewById<Button>(R.id.btnStartTime).setOnClickListener {
            if (findViewById<LinearLayout>(R.id.llStartTimeLayout).visibility == View.VISIBLE) {
                findViewById<Button>(R.id.btnStartTime).setBackgroundResource(R.drawable.create_challenge_uncompleted)
                findViewById<LinearLayout>(R.id.llStartTimeLayout).visibility = View.GONE
            }
            else {
                findViewById<Button>(R.id.btnStartTime).setBackgroundResource(R.drawable.create_challenge_completed)
                findViewById<LinearLayout>(R.id.llStartTimeLayout).visibility = View.VISIBLE
            }
        }

        findViewById<Button>(R.id.btnEndTime).setOnClickListener {
            if (findViewById<LinearLayout>(R.id.llEndTimeLayout).visibility == View.VISIBLE) {
                findViewById<Button>(R.id.btnEndTime).setBackgroundResource(R.drawable.create_challenge_uncompleted)
                findViewById<LinearLayout>(R.id.llEndTimeLayout).visibility = View.GONE
            }
            else {
                findViewById<Button>(R.id.btnEndTime).setBackgroundResource(R.drawable.create_challenge_completed)
                findViewById<LinearLayout>(R.id.llEndTimeLayout).visibility = View.VISIBLE
            }
        }

        findViewById<Button>(R.id.btnSetChallengePoint).setOnClickListener {
            if (findViewById<LinearLayout>(R.id.llSetChallengePoint).visibility == View.VISIBLE) {
                findViewById<Button>(R.id.btnSetChallengePoint).setBackgroundResource(R.drawable.create_challenge_uncompleted)
                findViewById<LinearLayout>(R.id.llSetChallengePoint).visibility = View.GONE
            }
            else {
                findViewById<Button>(R.id.btnSetChallengePoint).setBackgroundResource(R.drawable.create_challenge_completed)
                findViewById<LinearLayout>(R.id.llSetChallengePoint).visibility = View.VISIBLE
            }
        }

        lateinit var challengePoint: String
        findViewById<EditText>(R.id.etSetChallengePoint).addTextChangedListener(object : TextWatcher {
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
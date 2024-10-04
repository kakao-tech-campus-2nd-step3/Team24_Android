package com.example.challengeonairandroid.view.mypage

package com.example.challengeonairandroid.view.mypage.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.challengeonairandroid.R
import com.example.challengeonairandroid.databinding.ActivityMyPageProfileBinding
import com.example.challengeonairandroid.viewmodel.MyPageViewModel

class MyPageProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyPageProfileBinding
    private lateinit var viewModel: MyPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 데이터 바인딩 초기화
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_page_profile)

        // ViewModel 초기화
        viewModel = ViewModelProvider(this).get(MyPageViewModel::class.java)

        // 데이터 바인딩에 ViewModel 설정
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupUI()
    }

    private fun setupUI() {
        // 뒤로 가기 버튼 설정
        binding.btnBack.setOnClickListener {
            finish()
        }

        // 변경 완료 버튼 설정
        binding.btnComplete.setOnClickListener {
            viewModel.updateProfile()
            finish()
        }

        // EditText의 텍스트 변경 감지
        binding.etName.addTextChangedListener { text ->
            viewModel.updateUserNickName(text.toString())
        }
    }
}
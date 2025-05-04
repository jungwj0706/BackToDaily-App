package com.sunringenius.sunrinproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CardNewsActivity : AppCompatActivity() {
    private lateinit var goToGoalButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_news) // 레이아웃 파일 연결

        // 버튼 초기화
        goToGoalButton = findViewById(R.id.goToGoalButton)

        // 버튼 클릭 리스너 설정
        goToGoalButton.setOnClickListener {
            // GoalActivity로 이동
            val intent = Intent(this, GoalActivity::class.java)
            startActivity(intent)
        }
    }
}